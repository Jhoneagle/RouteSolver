# Käyttö-ohje

## Alkutoimet

### Suoritettava sovellus

Suoritettavan jaarin saa luotua kahdella tavalla. joko kirjoittamalla projektin juuressa _mvn package_, jolloin hakemistoon _target_ generoituu suoritettava jar-tiedoston _RouteSolver-1.0-SNAPSHOT.jar_. Toimivan jarin saa myös lataamalla uusimman [version](https://github.com/Jhoneagle/RouteSolver/releases).

### Tarvittavat tiedostot/hakemistot

Jotta ohjelma toimisi ja viellä halutulla tavalla, niin se tarvitsee kaksi asiaa. Nämä ovat konfiguratio-tiedosto ja itse kartta tiedosto. Näitä varteen tarvitaan aluksi kaksi kansiota, joiden nimet tulee olla _config_ ja _mapFiles_. Niiden tulee sijaita samassa paikassa, kuin suoritettava jar.

#### Konfiguraatio

_config_ kansioon luodaan tiedosto _config.properties_, jonka sisällöksi asetetaan seuraava

```
bassable=
unbassable=
```

Näihin kahteen riviin annetaan tieto siitä mitkä ascii-merkit edustavat kuljettavaa ja mitkä ei kuljettavaa aluetta. Esimerkiksi, jos merkit johon algoritmi ei saa mennä ovat _@_, _0_, _._ ja _E_. Kuljettavia alueita olisivat sen sijaan vain _1_. Tällöin rivit olisivat seuraavanlaiset

```
bassable=1
unbassable=@,0,.,E
```

Esimerkki tiedosto löytyy [täältä](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/example/config.properties).

#### Kartta

Karttojen tiedosto muodolla ei itsessään ole väliä kunhan tiedosto on tyylitön tekstitiedosto. Hyviä muotoja on esimerkiksi _.map_, _.txt_ ja _.md_. Sisällöksi tiedostolle asetetaan pelkästään ascii-merkiestä luotu matriisi, joka vastaa kooltaan konfiguraation koko tietoja.

Sisältö voi olla esimerkiksi seuraavanlainen, missä _1_ on kuljettavaa ja _0_ ei kuljettavaa maastoa.

```
00000
01110
00100
01110
00000
```

Kaikki kartat kuitenkin sijoitetaan kansioon _mapFiles_, joka sijaitsee jarin kanssa samassa kansiossa.

Jos omia karttoja ei kuitenkaan halua tehdä, niin valmiita löytyy muun muassa [Moving AI](https://www.movingai.com/benchmarks/grids.html) sivuilta. Ennen käyttöä niiden ensimmäiset 4 riviä kuitenkin täytyy poistaa, jotta _.MAP_ tiedostot sisältävät vain kartan tiedoston alusta lähtien.

## Ohjelman käyttö

Sovellus käynnistyy terminaalissa komennolla _java -jar (sovelluksen nimi).jar_ missä sovelluksen nimi on jarin generoinnin jälkeen _RouteSolver-1.0-SNAPSHOT.jar_.

Sovelluksen käynnistyttyä se lataa konfiguraatiot _config.properties_ tiedostosta tai ilmoittaa virheestä, jos ei onnistu. tämän jälkeen se tulostaa kaikki käytettävissä olevat käskyt selosteineen.

### komennot

* 'komennot' - palauttaa listan käskyistä, jota voi suorittaa ohjelmalla.
* 'lisaa kartta' - kysyy kartan sisältävän tiedoston nimeä. Jos se löytyy ja on oikeassa muodossa, niin sovellus lataa sen reitinhaku algoritmin tarjolle, muuten heittää virhe ilmoitusta.
* 'ratkaise reitti' - Jos ja vain jos kartta sekä konfiguraatio on onnistuneesti saatettu ohjelman tarjolle, niin laskee lyhimmän reitin kahden pisteen välillä. Aluksi toiminto kysyy ratkaistaanko reitti Jump point searchillä eli JPS:llä vai käytetäänkö A*. Sitten ohjelma kysyy tarvittavat koordinaatit, jonka jälkeen palauttaa saadun reitin pituuden.
* 'sammuta' - sulkee ohjelman.

