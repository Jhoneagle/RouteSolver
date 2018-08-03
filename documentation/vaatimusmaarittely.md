# Reitin ratkaisija

## Sovelluksen tarkoitus

Sovellus pystyy ratkaisemaan kaikenlaisia ascii-merkein luotuja sokkeloita. Pyrkien etsimään niistä lyhimmän/nopeimman/parhaan mahdollisen reitin. Kartta voi olla labyrintti, pohjapiirros tai mikä tahansa polun etsintään soveltuva haaste, joka on muodostettu ascii-merkeistä. Sovellus mahdollistaa karttojen yleisen koon määrittelyn. Sekä mitkä ovat ns. "läpi kuljettavia" merkkejä ja mitkä ei. 

Ascii karttojen tiedosto muodolla ja sisällöllä ei varsinaisesti ole merkitystä, jos merkki matriisi vastaa koolta konfigurointeja. Sekä konfiguroinneissa on määritelty kartassa esiintyvät merkit. Eikä se sisällä, kuin vain puhtaan merkki kartan. 

Sovellusta voi käyttää useammalla kartalla ja useammalla polun haulla samalla käyttökerralla, mutta konfigurointi ei onnistu, kuin alussa.

## Käyttäjät

Kuka tahansa kuka tarvitsee tietää lyhinmän reitti kartalla kahden pisteen välillä oli se labyrintti tai ascii-merkeillä muodostettu taulukko. Käyttäjä voi vapaasti hallita sovelluksen toimintaa kunhan noudattaa tiettyjä standardeja kartoissa ja säädöissä.

Käyttäjä voi muun muassa olla pelin pelaaja, joka haluaa tietää labyrintin tai pelin tason kartan lyhimmän reitin. Käyttäjä voi myös olla ihminen, joka on kiinnostunut tietämään parhaimman reitin jossakin paikassa pohjapiirroksen perusteella tai mitä tahansa parhaimman reitin hakuun liittyvää kysyvä ihminen. 

## Perusversion tarjoama toiminnallisuus

* yleisen karttojen koon määrittely käyttökerralle
* määrittelyn sille mihin merkeille algoritmi saa mennä ja mihin ei
* mahdollisuus tehdä useampia reittihakuja kerralla
* mahdollisuus vaihtaa karttaa kesken kaiken
* parhaimman reitin hakeminen
* saadun reitin pituuden kertominen
* saadun reitin näyttäminen visuaalisesti

## Jatkokehitysideoita

* tehokaampi ja tarkempi reitin haku
* mahdollisuus liikkua kaikkiin kahdeksaan suuntaan reitibn haussa (tällä hetkellä pystyy vain neljään)
* ominaisuus vaihtaa konfiguraatioita kesken käytön
* entistä parempi virhe tilanteiden käsittely
* varsinainen graafinen käyttöliittymä
