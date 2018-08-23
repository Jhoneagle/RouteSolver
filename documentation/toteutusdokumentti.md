# Toteutusdokumentti

Projekti koostuu käyttöliittymästä, kahdesta reitinhaku algoritmista, tiedoston lukemisesta sekä luoduista tietorakenteista. 

Molemmat reitinhaku algoritmit käyttävät yhteistä tekstuaalista käyttöliittymää. Mikä mahdollistaa niiden käytön lyhimmän reitin etsimiseen. Taas tiedoston lukua käytetään konfiguraatioiden ja karttojen hakemiseen ohjelman ulkopuolelta. 

## Reitinhaku

### Yleisrakenne



### O-ananlyysi ja vertailu

Molempien algoritmien koodin analysointi osoittaa hyvin samanlaisuutta pahimmantapauksen osalta. A*:n sekä JPS:n aikavaativuus on nimittäin _O(m*log(m))_ ja taas tilavaativuus on _O(m)_. Missä _m_ on kartan koko eli kartan leveyden ja korkeuden tulo. Kumassakin tilavaativuudessa vakio kertoimet vaativuusluokan sisällä on melko suuret johtuen siitä, että sama _Chell_-objekti yleensä esiintyy kahdesti sitä vastaavan ascii-merkin lisäksi. 

Molemmissa aikavaativuudessa _log(m)_ tulee niiden käyttämän minimikeon add- ja poll-metodien aikavaativuudesta. Sillä ne ovat molemmissa aikavaativuudelta suurimmat jokaisella kierroksella. Taas lineaarisuus tulee siitä, että varsinkin A* voi pahimmillaan tarkastella läpi kartan joka ikisen ruudun. jolloin minimikeon operaatiot suoritetaan pahimmillaan _m_ kertaa.

Keskiverto ja parhaimmassa tapauksessa A*:lla ja JPS:llä kuitenkin ero muuttuu jo suuremmaksi. Sillä keskimääräisesti JPS käy läpi paljon vähemmän _Chell_-objekteja. Myös tällöin aikaa vaihtoehtoisten reittien käyntiin kuluu vähemmän, koska monet vaihtoehdot karsitaan pois ilman niissä käyntiä. 

### Suorituskyky vertailu

Toteutusten varsinaisen suorituskyky testailun tulokset löytyvät [Täältä](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/testausdokumentti.md).

Tuloksista näkee 

## Parannettavaa

