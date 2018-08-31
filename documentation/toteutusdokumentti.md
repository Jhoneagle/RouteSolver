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

Tuloksista näkee 

## Parannettavaa

