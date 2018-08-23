# Toteutusdokumentti

Projekti koostuu k�ytt�liittym�st�, kahdesta reitinhaku algoritmista, tiedoston lukemisesta sek� luoduista tietorakenteista. 

Molemmat reitinhaku algoritmit k�ytt�v�t yhteist� tekstuaalista k�ytt�liittym��. Mik� mahdollistaa niiden k�yt�n lyhimm�n reitin etsimiseen. Taas tiedoston lukua k�ytet��n konfiguraatioiden ja karttojen hakemiseen ohjelman ulkopuolelta. 

## Reitinhaku

### Yleisrakenne



### O-ananlyysi ja vertailu

Molempien algoritmien koodin analysointi osoittaa hyvin samanlaisuutta pahimmantapauksen osalta. A*:n sek� JPS:n aikavaativuus on nimitt�in _O(m*log(m))_ ja taas tilavaativuus on _O(m)_. Miss� _m_ on kartan koko eli kartan leveyden ja korkeuden tulo. Kumassakin tilavaativuudessa vakio kertoimet vaativuusluokan sis�ll� on melko suuret johtuen siit�, ett� sama _Chell_-objekti yleens� esiintyy kahdesti sit� vastaavan ascii-merkin lis�ksi. 

Molemmissa aikavaativuudessa _log(m)_ tulee niiden k�ytt�m�n minimikeon add- ja poll-metodien aikavaativuudesta. Sill� ne ovat molemmissa aikavaativuudelta suurimmat jokaisella kierroksella. Taas lineaarisuus tulee siit�, ett� varsinkin A* voi pahimmillaan tarkastella l�pi kartan joka ikisen ruudun. jolloin minimikeon operaatiot suoritetaan pahimmillaan _m_ kertaa.

Keskiverto ja parhaimmassa tapauksessa A*:lla ja JPS:ll� kuitenkin ero muuttuu jo suuremmaksi. Sill� keskim��r�isesti JPS k�y l�pi paljon v�hemm�n _Chell_-objekteja. My�s t�ll�in aikaa vaihtoehtoisten reittien k�yntiin kuluu v�hemm�n, koska monet vaihtoehdot karsitaan pois ilman niiss� k�ynti�. 

### Suorituskyky vertailu

Toteutusten varsinaisen suorituskyky testailun tulokset l�ytyv�t [T��lt�](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/testausdokumentti.md).

Tuloksista n�kee 

## Parannettavaa

