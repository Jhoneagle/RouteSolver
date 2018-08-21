# Testausdokumentti

## Yksikk� testaus

Projektin testien rivikattavuus on 97% ja haarautumakattavuus on 87%. 

![testikattavuus](kuvat/testikattavuus.jpg)

Haarautumakattavuuden osalta oma tekoiset tietorakenteet ovat heikoin alue projektista 83%. T�m� johtunee siit�, ett� tietorakenteiden kaikkien metodien vaihtoehtoja ei kannata jokaista testata erikseen eik� tule normaalisti vastaan. N�ist� ei testatuista haarautumista monet kuitenkin ovat harvinaisia erityistapauksia, joten ne eiv�t ole olennaisia. 

Yksikk�testeille suoraan testattuja luokkia ovat luokat, joissa meodit tekev�t jotakin yksinkertaisuudesta poikeavaa. Kaikki testit ovat toteutettu Junitilla, jolloin n�iden toistaminen tapahtuu helposti mavenin avulla suorittamalla: _mvn test_.

### DataListTest ja MinHeapTest

Testaavat oma tekoisten tietorakenteiden operaatioita pyrkien simuloimaan sen yksinkertaista k�ytt��. Kuitenkin varmistaen v�ltt�m�tt�m�t asiat yksinkertaisilla testeill�. 

Listaa ja pinoa simuloiva _DataList_ testataan erityisesti sen osalta, ett� se pit�� tietueet alkuper�isess� j�rjestyksess�. Mist� seuraa, ett� mik� lis�t��n vikana voidaan poistaa ekana ja kaikki tietueet l�ydet��n samassa j�rjestyksess�, kuin lis�ttiin. 

Taas minimikekoa simuloiva _MinHeap_ varmistetaan, ett� se tarjoaa lis��misj�rjestyksest� huolimatta pyydett�ess� ensimm�isen arvon. Minimikeon kohdalla t�m� tarkoittaa vertailun perusteella pienint� tietuetta, joka l�ytyy. 

### ReaderTest

On tarkoitus testata ohjelman tiedostonluku toiminnallisuutta varmistaakseen, ett� tiedosto luetaan oikein. My�s varmistaakseen, ett� ep�onnistuessa virhe k�sitell��n oikein. 

### MapTest

Testaa luokan mik� sis�lt�� varsinaisen kartan, jonka algoritmi saa. Sek�, joka huolehtii kartaan liittyvist� olennaisista operaatioista. Luokan kattava testaaminen ja toiminnan oikeellisuuden varmistaminen on hyvin olennaista. Sill� kyseisen luokan metodit ovat elint�rkeit� algoritmin toimivuudelle. 

### FinderTest

Testaa itse varsinaiset reitti algoritmit. T�h�n se k�ytt�� valmiiksi luotua kartaa, jossa on pyritty simuloimaa valta osa yleisimmist� ongelma tapauksista. Molemmille algoritmeille suoritetaan samalla kartalla 8 reitti�, joiden ideaali tulos on laskettu k�sin.

K�ytetty kartta on seuraavanlainen

```
0000000000000000000000
0111111011011111110110
0000001010000000010100
0111101011111111010110
0010101001000101010010
0010101111000101011110
0010101001000101010010
0011101011110111010110
0010111010011101110100
0010101011000101010110
0110101001101101010010
0010111100001000010110
0010000100111010010000
0111111111101111110110
0000001010000000010100
0111101011001111010110
0010101001000101010010
0010101111111101011110
0010101001010101010010
0011101011010111010110
0010111010000101110100
0010101011010101010110
0110101001011101010010
0000000000000000000000
```

## Empiirinen Testaus

Ohjelman toimintaan on sis��nrakennettu komennot suorituskyky testausta varten. Komennot ovat _timeTest_ ja _memoryTest_ mitk� toimivat huolimatta suorittaako ohjelman suoraan l�hdekoodina vai suoritettavan jarrina. 

Suoritusajan testaus on tapahtunut k�ytt�m�ll� komentoa _timeTest_, joka suorittaa saman reitin haun 10 kertaa ja tulostaa jokaiseen kierrokseen menneen ajan. Kuitenkin niin, ett� jokaisen sarjan j�lkeen sovellus on k�ynnistetty uudestaan, jotta v�ltett�isiin toistuvuudesta aiheutuva nopeutuminen, mink� tietokoneiden v�limuisti aiheuttaa. 

