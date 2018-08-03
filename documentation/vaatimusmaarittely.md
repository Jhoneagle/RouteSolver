# Reitin ratkaisija

## Sovelluksen tarkoitus

Sovellus pystyy ratkaisemaan kaikenlaisia ascii-merkein luotuja sokkeloita. Pyrkien etsim��n niist� lyhimm�n/nopeimman/parhaan mahdollisen reitin. Kartta voi olla labyrintti, pohjapiirros tai mik� tahansa polun etsint��n soveltuva haaste, joka on muodostettu ascii-merkeist�. Sovellus mahdollistaa karttojen yleisen koon m��rittelyn. Sek� mitk� ovat ns. "l�pi kuljettavia" merkkej� ja mitk� ei. 

Ascii karttojen tiedosto muodolla ja sis�ll�ll� ei varsinaisesti ole merkityst�, jos merkki matriisi vastaa koolta konfigurointeja. Sek� konfiguroinneissa on m��ritelty kartassa esiintyv�t merkit. Eik� se sis�ll�, kuin vain puhtaan merkki kartan. 

Sovellusta voi k�ytt�� useammalla kartalla ja useammalla polun haulla samalla k�ytt�kerralla, mutta konfigurointi ei onnistu, kuin alussa.

## K�ytt�j�t

Kuka tahansa kuka tarvitsee tiet�� lyhinm�n reitti kartalla kahden pisteen v�lill� oli se labyrintti tai ascii-merkeill� muodostettu taulukko. K�ytt�j� voi vapaasti hallita sovelluksen toimintaa kunhan noudattaa tiettyj� standardeja kartoissa ja s��d�iss�.

K�ytt�j� voi muun muassa olla pelin pelaaja, joka haluaa tiet�� labyrintin tai pelin tason kartan lyhimm�n reitin. K�ytt�j� voi my�s olla ihminen, joka on kiinnostunut tiet�m��n parhaimman reitin jossakin paikassa pohjapiirroksen perusteella tai mit� tahansa parhaimman reitin hakuun liittyv�� kysyv� ihminen. 

## Perusversion tarjoama toiminnallisuus

* yleisen karttojen koon m��rittely k�ytt�kerralle
* m��rittelyn sille mihin merkeille algoritmi saa menn� ja mihin ei
* mahdollisuus tehd� useampia reittihakuja kerralla
* mahdollisuus vaihtaa karttaa kesken kaiken
* parhaimman reitin hakeminen
* saadun reitin pituuden kertominen
* saadun reitin n�ytt�minen visuaalisesti

## Jatkokehitysideoita

* tehokaampi ja tarkempi reitin haku
* mahdollisuus liikkua kaikkiin kahdeksaan suuntaan reitibn haussa (t�ll� hetkell� pystyy vain nelj��n)
* ominaisuus vaihtaa konfiguraatioita kesken k�yt�n
* entist� parempi virhe tilanteiden k�sittely
* varsinainen graafinen k�ytt�liittym�
