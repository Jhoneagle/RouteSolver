# Reitin ratkaisija

## dokumentaatio

[Työaikakirjanpito](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/tuntikirjanpito.md) 

[Testausdokumentti](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/testausdokumentti.md)

[Vaatimusmäärittely](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/vaatimusmaarittely.md) 

## Viikkoraportit

[Viikkoraportti 1](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/raportit/viikko1.md)

[Viikkoraportti 2](https://github.com/Jhoneagle/RouteSolver/blob/master/documentation/raportit/viikko2.md)

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
