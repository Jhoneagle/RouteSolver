# Toteutusdokumentti

Projekti koostuu k�ytt�liittym�st�, kahdesta reitinhaku algoritmista, tiedoston lukemisesta sek� luoduista tietorakenteista. 

Molemmat reitinhaku algoritmit k�ytt�v�t yhteist� tekstuaalista k�ytt�liittym��. Mik� mahdollistaa niiden k�yt�n lyhimm�n reitin etsimiseen. Taas tiedoston lukua k�ytet��n konfiguraatioiden ja karttojen hakemiseen ohjelman ulkopuolelta. 

## Reitinhaku

### Yleisrakenne

Molemmat A* ja Jump point search (JPS) ovat alustukseltaan vastaavat ja varsinaisesti n�iss� on eroa siin�, kuinka mahdolliset seuraajat valitaan. Sek� mik� on kriteerien j�rjestys. 

Molemmat k�ytt�v�t hyv�kseen p��asiallisesti 3 tietorakennetta. N�ist� ensimm�inen on _Map_, joka sis�lt�� varsinaisen ascii-kartan, jonka k�ytt�j� antaa kaksiulotteisena taulukkona. Sen lis�ksi se sis�lt�� kaikki algoritmien k�ytt�m�t suoraan ascii-kartaan liittyv�t operaatiot. Kuten pystyyk� ruutuun menem��n, mik� on kahden ruudun v�linen hinta, miten kaukana kaksi pistett� ovat toisistaan ja  onko ruutu kartan sis�ll�. 

Toinen taas on _MinHeap_, mik� implementoi minimikekoa ja toimii algoritmissa varsinaisena jonona. Pyritt�ess� valitsemaan jatkuvasti paras seuraava valinta vaihtoehdoista johon voidaan menn�. Se s�ilytt�� kaikki avoimet ruudut _Chell_ objekteina, jotka j�rjestet��n et�isyysarvion mukaan. 

Viimeinen tietorakkenne on _Chell_ objekteista luodut taulukkot. Ensimm�inen kahdesta s�ilytt�� tiedon siit� onko paikassa k�yty sek� tiedon parhaimmasta et�isyydest� alun kanssa sek� arvion loppuun. Taas toinen sis�lt�� tiedon, mik� oli ruudun edelt�j� eli mist� tultiin tarkastelun kohteena olevaan ruutuun. 

Jokaiselle ruutua imitoivalle _Chell_ objektille, joka jonosta saadaan suoritetaan samat toimen piteet. Ne kuitenkin vaihtelevat algoritmien v�lill�.

A*:ssa sen kaikki kahdeksan naapuria tarkistetaan. Kaikki, jotka kelpaavat seuraajaksi lis�t��n jonoon, jossa ne j�rjest�ytyy minimikeon mukaisesti oikeaan paikaan. Jotta sitten voidaan ottaa ensimm�isen� jonossa oleva, mik� on seuraava paras tutkittava avoin ruutu. Mik� jatkuu kunnes kohde ruutu tulee jonosta ulos.

Sen sijaan JPS kaikki kahdeksan naapuria tarkistetaan ja jos niihin voi menn�, niin niiden suunasta aletaan etsim��n seuraavaa hyppy pistett�. Jos hyppy piste l�ytyy kyseisest� suunasta, niin se lis�t��n jonona toimivaan minimikekoon. Muussa tapauksessa menn��n eteenp�in. Hyppy pisteen etsint��n k�ytet��n rekursiivista metodia erityisesti, koska diagonaalisesti liikkuessa pit�� my�s aina tarkistaa sivuttais- ja pystysuunta. 

### O-ananlyysi ja vertailu

Molempien algoritmien koodin analysointi osoittaa hyvin samanlaisuutta pahimmantapauksen osalta. A*:n sek� JPS:n aikavaativuus on nimitt�in _O(m*log(m))_ ja taas tilavaativuus on _O(m)_. Miss� _m_ on kartan koko eli kartan leveyden ja korkeuden tulo. Kumassakin tilavaativuudessa vakio kertoimet vaativuusluokan sis�ll� on melko suuret johtuen siit�, ett� sama _Chell_-objekti yleens� esiintyy kahdesti sit� vastaavan ascii-merkin lis�ksi. 

Molemmissa aikavaativuudessa _log(m)_ tulee niiden k�ytt�m�n minimikeon add- ja poll-metodien aikavaativuudesta. Sill� ne ovat molemmissa aikavaativuudelta suurimmat jokaisella kierroksella. Taas lineaarisuus tulee siit�, ett� varsinkin A* voi pahimmillaan tarkastella l�pi kartan joka ikisen ruudun. jolloin minimikeon operaatiot suoritetaan pahimmillaan _m_ kertaa.

Keskiverto ja parhaimmassa tapauksessa A*:lla ja JPS:ll� kuitenkin ero muuttuu jo suuremmaksi. Sill� keskim��r�isesti JPS k�y l�pi paljon v�hemm�n _Chell_-objekteja. My�s t�ll�in aikaa vaihtoehtoisten reittien k�yntiin kuluu v�hemm�n, koska monet vaihtoehdot karsitaan pois ilman niiss� k�ynti�. 

### Suorituskyky vertailu

Toteutusten varsinaisen suorituskyky testailun tulokset l�ytyv�t [T��lt�](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/testausdokumentti.md).

Tuloksista n�kee 

## Parannettavaa

