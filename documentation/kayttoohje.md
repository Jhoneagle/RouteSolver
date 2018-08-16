# Käyttö-ohje

## Alkutoimet

### Suoritettava sovellus

Suoritettavan jaarin saa luotua kahdella tavalla. joko kirjoittamalla projektin juuressa _mvn package_, jolloin hakemistoon _target_ generoituu suoritettava jar-tiedoston _RouteSolver-1.0-SNAPSHOT.jar_. Toimivan jarin saa myös lataamalla uusimman [version](https://github.com/Jhoneagle/RouteSolver/releases/tag/v1.0).

### Tarvittavat tiedostot/hakemistot

Jotta ohjelma toimisi ja viellä halutulla tavalla, niin se tarvitsee kaksi asiaa. Nämä ovat konfiguratio-tiedosto ja itse kartta tiedosto. Näitä varteen tarvitaan aluksi kaksi kansiota, joiden nimet tulee olla _config_ ja _mapFiles_. Niiden tulee sijaita samassa paikassa, kuin suoritettava jar.

#### konfiguraatio

_config_ kansioon luodaan tiedosto _config.properties_, jonka sisällöksi asetetaan seuraava

```
x=
y=
bassable=
unbassable=
```

Ensimmäisiin kahteen riviin asetetaan heti yhtäsuuruusmerkin jälkeen ohjelmalle annettujen karttojen yleisen leveyden ja korkeuden. Ohjelma käyttää tätä tietoa estääkseen liian suurien karttojen antamisen ohjelmalle.

Taas kahteen seuraavaan annetaan tieto siitä mitkä ascii-merkit edustavat kuljettavaa ja mitkä ei kuljettavaa aluetta. Esimerkiksi, jos merkit johon algoritmi ei saa mennä ovat @, 0, . ja E. Kuljettavia alueita olisivat sen sijaan vain 1. Tällöin rivit olisivat seuraavanlaiset

```
bassable=1
unbassable=@,0,.,E
```

#### kartta

