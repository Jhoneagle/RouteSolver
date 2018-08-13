# Reitin ratkaisija

## Sovelluksen tarkoitus

Sovellus pystyy ratkaisemaan kaikenlaisia ascii-merkein luotuja sokkeloita. Pyrkien etsimään niistä lyhimmän/nopeimman/parhaan mahdollisen reitin. Kartta voi olla labyrintti, pohjapiirros tai mikä tahansa polun etsintään soveltuva haaste, joka on muodostettu ascii-merkeistä. Sovellus mahdollistaa karttojen yleisen koon määrittelyn. Sekä mitkä ovat ns. "läpi kuljettavia" merkkejä ja mitkä ei. 

Ascii karttojen tiedosto muodolla ja sisällöllä ei varsinaisesti ole merkitystä, jos merkki matriisi vastaa koolta konfigurointeja. Sekä konfiguroinneissa on määritelty kartassa esiintyvät merkit. Eikä se sisällä, kuin vain puhtaan merkki kartan. 

Sovellusta voi käyttää useammalla kartalla ja useammalla polun haulla samalla käyttökerralla, mutta konfigurointi ei onnistu, kuin alussa.

## Käyttäjät

Kuka tahansa kuka tarvitsee tietää lyhinmän reitti kartalla kahden pisteen välillä oli se labyrintti tai ascii-merkeillä muodostettu taulukko. Käyttäjä voi vapaasti hallita sovelluksen toimintaa kunhan noudattaa tiettyjä standardeja kartoissa ja säädöissä.

Käyttäjä voi muun muassa olla pelin pelaaja, joka haluaa tietää labyrintin tai pelin tason kartan lyhimmän reitin. Käyttäjä voi myös olla ihminen, joka on kiinnostunut tietämään parhaimman reitin jossakin paikassa pohjapiirroksen perusteella tai mitä tahansa parhaimman reitin hakuun liittyvää kysyvä ihminen. 

## Input ja output

Sovellukselle annetan konfiguraatiot properties-tiedostona, jossa on olemassa tietyt kohdat. Sekä tekstitiedosto, joka sisältää itse kartan merkkien "merenä". Lopuksi ohjelmalle annetaan lähtö ja pääte koordinaatit ja algoritmi ratkaistuaan antaa takaisin lyhimmän reitin koordinaattien pinona. Mistä ohjelma kertoo pituuden eli kuinka pitkä lyhin reitti on.

## Algoritmi

Reitinhakuun käytetään alussa A* reitinhaku algoritmia ja siitä tehdään laajennettu versio, joka on JPS eli jump point search algoritmi. Näitä vertaillaan reitinhaku algoritmeista toisiinsa, sillä ne ovat oletusarvoisesti nopeita. Tämä johtuu siitä, koska ne käyttävät etäisyysarvioita määrittäessään parhaan suunnan jatkaa reitin etsintää. Kuitenkin aina muistaen jokaisen polun, niin pitkälle, kun se on ollut ideaali vaihtoehto. 

Projektin tarkoituksena on verrata niiden suorituskykyjä toisiinsa ja nähdä onko JPS merkittävästi normaalia A* parempi reitinhaussa. Käytettäessä karttoina kaksiulotteisia ascii-taulukoita.

Tavoitteena, että algoritmeista tehokaampi saavuttaisi O-analyysissa aika ja tilavaativuudeksi O(n) missä "n" tarkoittaa polun pituutta. Eli, että ratkaisu löytyisi lineaarisessa ajassa suhteessa ratkaisun pituuteen, sillä tällöin ei käytäisi merkittävissä määrin turhia reittejä läpi vaan kuljettaisiin mahdollisimman oikein heti alussa. 

## Tietorakenteet

Käytettäviä tietoranteita ovat priorityqueuea, listaa, taulukkoa ja pinoa. Listaa tarvitaan lähinnä tiedoston lukemisen ohella, kun tekstitiedostosta tehdään matriisi. Taas pinoa eli järjestettyä jonoa tarvitaan lopullisen polun palauttamisessa. Taas  taulukoita käytetään ascii-kartan esittämiseen, sekä soluissa käynnin ja niiden etäisyys arvioiden tallessa pitoon. Varsinainen reitinhaku taas käyttää priorityqueueta saadakseen tutkittavaksi aina parhaan vaihtoehdon soluista, jotka ovat auki. 

## Perusversion tarjoama toiminnallisuus

* yleisen karttojen koon määrittely käyttökerralle
* määrittelyn sille mihin merkeille algoritmi saa mennä ja mihin ei
* mahdollisuus tehdä useampia reittihakuja kerralla
* mahdollisuus vaihtaa karttaa kesken kaiken
* parhaimman reitin hakeminen
* mahdollisuus liikkua kaikkiin kahdeksaan suuntaan reitinhaussa
* saadun reitin pituuden kertominen

## Jatkokehitysideoita

* tehokaampi ja tarkempi reitin haku
* ominaisuus vaihtaa konfiguraatioita kesken käytön
* entistä parempi virhe tilanteiden käsittely
* varsinainen graafinen käyttöliittymä
* saadun reitin näyttäminen visuaalisesti
