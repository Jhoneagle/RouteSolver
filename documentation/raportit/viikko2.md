# Viikkoraportti 2

Projekti on edennyt hyvin. Sovellus on jo suorituskelpoinen ohjelma vaikkakin suhteellisen yksinkertainen viel�. Reitinhaku algoritmi pohjautuu A*:iin, mutta on siit� jonkin verran muokattu versio. 

Ohjelmalle on my�s tehty Javadocia ja Junit testausta, jotka molemmat etenev�t hyvin. 

Algoritmin kehtiys tuotti pitk��n vaikeuksia, sill� se jouduttiin ongelmien ja toimimattomuuden vuoksi uudelleen kirjoittamaan useampaan kertaan. Mist� seurasi paljon turhia ty� tunteja. 

Taulukoiden k�ytt� taidot ovat ainakin parantuneet ja ehk� hieman lis�� oppinut my�s A*:ista. Yleisesti ottaen asiat kuitenkin olleet hanskassa jo entuudestaan. 

Seuraavaksi Javadocin ja Junit testauksen loppuun teko nykyiselle versiolle. Sek� algoritmin tehokuuden parantaminen ja mahdollisesti muisti tarpeen kevennys isoilla ascii kartoilla. Sill� t�ll� hetkell� sovellus kaatuu muistin puutteeseen jo, kun manhattan et�isyys pisteiden v�lill� ylitt�� n. 150. Erityisesti, jos v�lill� on esteit�, jotka aiheuttavat kiert�mist�.  