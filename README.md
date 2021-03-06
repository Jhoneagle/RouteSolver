﻿# Reitin ratkaisija

Lyhimmän reitin ascii-kartalla etsivä ohjelma, joka käyttää sekä A*, että Jump point searchiä (JPS).

## Release

Suoritettavan jarin löytää [täältä](https://github.com/Jhoneagle/RouteSolver/releases).

## Dokumentaatio

[Työaikakirjanpito](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/tuntikirjanpito.md) 

[Testausdokumentti](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/testausdokumentti.md)

[Toteutusdokumentti](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/toteutusdokumentti.md)

[Vaatimusmäärittely](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/vaatimusmaarittely.md) 

[Käyttö-ohje](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/kayttoohje.md) 

## Viikkoraportit

[Viikkoraportti 1](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/reports/viikko1.md)

[Viikkoraportti 2](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/reports/viikko2.md)

[Viikkoraportti 3](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/reports/viikko3.md)

[Viikkoraportti 4](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/reports/viikko4.md)

[Viikkoraportti 5](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/reports/viikko5.md)

[Viikkoraportti 6](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/reports/viikko6.md)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _RouteSolver-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/Jhoneagle/RouteSolver/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
