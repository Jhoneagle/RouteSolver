# Toteutusdokumentti

Projekti koostuu käyttöliittymästä, kahdesta reitinhaku algoritmista, tiedoston lukemisesta sekä luoduista tietorakenteista. 

Molemmat reitinhaku algoritmit käyttävät yhteistä tekstuaalista käyttöliittymää. Mikä mahdollistaa niiden käytön lyhimmän reitin etsimiseen. Taas tiedoston lukua käytetään konfiguraatioiden ja karttojen hakemiseen ohjelman ulkopuolelta. 

## Reitinhaku

### Yleisrakenne

Molemmat A* ja Jump point search (JPS) ovat alustukseltaan vastaavat ja varsinaisesti näissä on eroa siinä, kuinka mahdolliset seuraajat valitaan. Sekä mikä on kriteerien järjestys. 

Molemmat käyttävät hyväkseen pääasiallisesti 3 tietorakennetta. Näistä ensimmäinen on _Map_, joka sisältää varsinaisen ascii-kartan, jonka käyttäjä antaa kaksiulotteisena taulukkona. Sen lisäksi se sisältää kaikki algoritmien käyttämät suoraan ascii-kartaan liittyvät operaatiot. Kuten pystyykö ruutuun menemään, mikä on kahden ruudun välinen hinta, miten kaukana kaksi pistettä ovat toisistaan ja  onko ruutu kartan sisällä. 

Toinen taas on _MinHeap_, mikä implementoi minimikekoa ja toimii algoritmissa varsinaisena jonona. Pyrittäessä valitsemaan jatkuvasti paras seuraava valinta vaihtoehdoista johon voidaan mennä. Se säilyttää kaikki avoimet ruudut _Chell_ objekteina, jotka järjestetään etäisyysarvion mukaan. 

Viimeinen tietorakkenne on _Chell_ objekteista luodut taulukkot. Ensimmäinen kahdesta säilyttää tiedon siitä onko paikassa käyty sekä tiedon parhaimmasta etäisyydestä alun kanssa sekä arvion loppuun. Taas toinen sisältää tiedon, mikä oli ruudun edeltäjä eli mistä tultiin tarkastelun kohteena olevaan ruutuun. 

Jokaiselle ruutua imitoivalle _Chell_ objektille, joka jonosta saadaan suoritetaan samat toimen piteet. Ne kuitenkin vaihtelevat algoritmien välillä.

A*:ssa sen kaikki kahdeksan naapuria tarkistetaan. Kaikki, jotka kelpaavat seuraajaksi lisätään jonoon, jossa ne järjestäytyy minimikeon mukaisesti oikeaan paikaan. Jotta sitten voidaan ottaa ensimmäisenä jonossa oleva, mikä on seuraava paras tutkittava avoin ruutu. Mikä jatkuu kunnes kohde ruutu tulee jonosta ulos.

Sen sijaan JPS kaikki kahdeksan naapuria tarkistetaan ja jos niihin voi mennä, niin niiden suunasta aletaan etsimään seuraavaa hyppy pistettä. Jos hyppy piste löytyy kyseisestä suunasta, niin se lisätään jonona toimivaan minimikekoon. Muussa tapauksessa mennään eteenpäin. Hyppy pisteen etsintään käytetään rekursiivista metodia erityisesti, koska diagonaalisesti liikkuessa pitää myös aina tarkistaa sivuttais- ja pystysuunta. 

### O-ananlyysi ja vertailu

Molempien algoritmien koodin analysointi osoittaa hyvin samanlaisuutta pahimmantapauksen osalta. A*:n sekä JPS:n aikavaativuus on nimittäin _O(m*log(m))_ ja taas tilavaativuus on _O(m)_. Missä _m_ on kartan koko eli kartan leveyden ja korkeuden tulo. Kumassakin tilavaativuudessa vakio kertoimet vaativuusluokan sisällä on melko suuret johtuen siitä, että sama _Chell_-objekti yleensä esiintyy kahdesti sitä vastaavan ascii-merkin lisäksi. 

Molemmissa aikavaativuudessa _log(m)_ tulee niiden käyttämän minimikeon add- ja poll-metodien aikavaativuudesta. Sillä ne ovat molemmissa aikavaativuudelta suurimmat jokaisella kierroksella. Taas lineaarisuus tulee siitä, että varsinkin A* voi pahimmillaan tarkastella läpi kartan joka ikisen ruudun. jolloin minimikeon operaatiot suoritetaan pahimmillaan _m_ kertaa.

Keskiverto ja parhaimmassa tapauksessa A*:lla ja JPS:llä kuitenkin ero muuttuu jo suuremmaksi. Sillä keskimääräisesti JPS käy läpi paljon vähemmän _Chell_-objekteja. Myös tällöin aikaa vaihtoehtoisten reittien käyntiin kuluu vähemmän, koska monet vaihtoehdot karsitaan pois ilman niissä käyntiä. 

### Suorituskyky vertailu

Toteutusten varsinaisen suorituskyky testailun tulokset löytyvät [täältä](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/testausdokumentti.md#suorituskyky-testaus).

Tuloksista näkee, että nopeuden puolesta A* on varsinkin pitkillä reiteillä reilusti nopeampi. Sen sijaan muistin käytöllisesti A* häviää Jump point searchille sitä voimakkaammin mitä suurempi reitti. 

#### Muisti

JPS:n käyttämä muisti pysyy neljäsä viidestä kuvaajasta lähes vakiona. Mistä voisi päätellä, että yleisesti ottaen se pystyy ratkaisemaan lyhimmän reitin pituudestaan huolimatta lähes vakio muistilla. Se ylittää 1,5MB heiton vasta kartan koon saapuessa lähemmäs _700x700_ ja tämänkin jälkeen se on parhaimmillaan _768x768_ kartasssa. Missä se nousee pienimmän ja suurimman muistin tarpeen välillä melkein 5MB.

Taas A*:n muistin tarve reitin ratkaisussa kasvaa reipaampaa tahtia reitinpituuden kanssa. Se näyttäisi jopa kiihtyvän sitä myötä mitä suuremmasta matkasta kyse, koska sitä useampia mahdollisia reittejä se käy läpi ja turhia välejä tutkii. 

JPS:n muistin tarpeen pääasiallinen vaikuttaja on siis kartan koko, mutta senkin vaikutus on suhteellisen matala, koska kartan koolla _1024x1024_ huippu ylettää juuri ja juuri samoihin lukemiin, josta A* joutuu aloittamaan aina. Jolloin A*:n muisin tarpeeseen vaikuttaa paljon voimakaammin reitin pituus, mutta pienimpiin arvoihin myös kartan koko.

#### SuoritusAika

A*:n ajan käyttö tuloksen löytämiseen on hyvin vakaa. Se nousee hyvin rauhallisesti polun pituuden kasvaessa, kunnes reitti lähestyy 800 rajaa. Tämän jälkeen se alkaakin jo sitten kasvaa nopeampaa tahtia. Yleisesti sen suoritusaika on kuitenin hyvin maltillinen, koska reitinpituuden olessa 1500 tuntumassa käyttää A* vajaa 200ms sen löytämiseen kuitenkin.

Sen sijaan JPS ei ole todellakaan kovin tehokas reitinhaku algoritmi mitä tulee suoritusaikaan. Sen ajan käyttö nousee melkein exponentiaalisesti polun pituuteen nähden. Tähän kuitenkin todennäköisesti vaikuttaa myös jonkin verran kartan kasvu. Se ylittää sekunnin jo kun reitin pituus on vasta vajaa 1000. 

Eli siinä missä A* käyttää lähes lineaarisen määrän aikaa reitinhakuun polun pituuden suhteen, niin JPS muuttuu tehottomaksi jo hyvin nopeasti lähes exponentiaalisen kasvun vuoksi. Se kykenee lähinnä vastaamaan A* nopeudessa, jos reitti on aivan suora eikä pituudeltaan kovin pitkä.

#### Yhteenveto

Reitinhaussa A* ja JPS ovat selvästi hyvin erilaiset ja eritasoiset eri asioissa. Näiden implementaatioiden suoraan järjestely parhaimmuuteen ei onnistu tulosten vuoksi. 

Jos reitinhaun tarvitsee olla hyvin nopeaa eikä musitin käytöllä ole niin väliä. Tällöin A* on näistä ylivoimaisesti parempi. Se kykenee ratkaisemaan inhimillisessä ajassa (alle 200ms) suurissakin kartoissa äärimmäisiä reittejä. A* implementaatio olisi siis hyvä esimerkiksi peleissä tietokoneen liikkumisen suunnitteluun.

Sen sijaan, jos muistia on rajallisesti käytössä eikä suoritusajalla ole, niin merkitystä. Tällöin JPS on oikea ratkaisu, koska sen muistin käyttö on täysin hillitty eikä pyydä merkittävästi enempää huolimatta minkä pituinen reitti on kyseessä, kunhan kartan koon vaatima muisti tarve täytyy. Sen suoritusaika kuitenkin on hidas pitkää reittiä etsiessä. Jolloin se on kätevä muun muassa etsiessä optimaallista reittiä erittäin suurissa kartoissa. 

## Parannettavaa

Vaikka reitinhaku algoritmit toimivat suhteellisen hyvin. A* on oikeassa aikavaativuus luokassa, kun taas JPS:n tilavaativuus on hyvänlainen. Niillä on kuitenkin melko paljon kehitettävää viellä vastakaisissa vaativuuksissa. Kiintoisa ero on tosin siinä, että O-analyysi viitaisi kummankin olevan lähellä optimaalista aika- ja tilavaativuus luokaa mihin reitinhaku algoritmi voisi päästä. Empiirisesti testattuna tämä ei kuitenkaan aivan näy. 

A*:n implementaation muistin käyttöä voisi pyrkiä kehittämään niin, että se edes puolittuisi siitä mitä se nyt on. Tämän voisi saada aikaan parantelemalla sitä mitä vaihtoehtoja edes käydään läpi ja mitä ei. Vaihtoehtoisesti muistia saattaisi pystyä säästämään suorituksen aikana poistamalla tietueita, joissa ei mitenkään voida enää käydä. Kaikista muista paikoista, kuin _path_-taulusta, jota käytetään reitin kokoamiseen lopusta alkuun. 

Taas JPS:n suoritusaikaa voisi ehdottomasti vielä hioa tekemällä jokaisesta kierroksesta sutjakkaamman. Esimerkiksi nopeuttamalla hyppy pisteiden etsintää entuudestaan tai luomalla jonkinlaista reitin spekulointia parhaan ehdokkaan valintaan. 

