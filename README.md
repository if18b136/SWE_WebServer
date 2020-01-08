# SWE_WebServer
BIF 3D1 Software Engineering Project.

<h2>Benutzerhandbuch</h2>

Die Applikation kann über einen belibigen Browser gestartet werden - Anmerkung hierzu: getestet wurden die Features nur auf Mozilla Firefox und Google Chrome.
Dafür muss vorerst über Apache xampp sowohl der Serverhost als auch die SQL Datenbank gestartet werden. Auch ist Vorsicht geboten bei transfer des gesamten Webserverprojekts in andere Verzeichnisse, da ein paar Referenzen nur als absolute Pfade angegeben werden konnten.

Als User kann man sich wie folgt verbinden: **>>Host-IP<<:8081**
Auf Port 8080 befindet sich die SQL Datenbank.
Man gelangt Auf die simple Startseite und kann über die Navbar auf der Oberseite zu den verschiedenen Plugins navigieren.

<h3>Temperatur Plugin</h3>
Bei Auruf des Links werden dem User alle Einträge aus der Temperaturdatenbank aufgelistet. Der User kann blättern, Anzahl der Einträge einstellen und auch nach Einträgen Suchen.
Wenn der user als URL **>>Host-IP<<:8081/GetTemperature/>>Jahr<</>>Monat<</>>Tag<<** eingibt erhält er ein XML File mit den gefundenen Daten von dem exakten Tag zurück.

<h3>To Lower Plugin</h3>
Hier kann ein Text samt Sonderzeichen in Kleinbuchstaben transformiert werden - den Text dazu einfach im Textfeld eingeben.

<h3>Static Plugin</h3>
Das statische Plugin besitzt keinen eigenen Link, da es zu jeder Zeit aufgerufen werden kann, indem der Pfad zu einer Datei, die sich im statischen Verzeichnis befindet als Anhang zur URL hinzugefügt wird.

<h3>Navigation Plugin</h3>
Das Navigationsplugin besitzt zwei Buttons und ein  textfeld. Über den Load Data Button kann die ausgewählte OSM in eine Hatshtable geladen werden. In das Textfeld kann ein Straßenname eingegeben und überprüft werden, ob diese Straße in der Karte vorhanden ist.


<h2>Lösungsbeschreibung</h2>

Der Webserver wurde als Einzelprojekt umgesetzt, indem zuerst die 4 ersten Uebungsaufgaben mitsamt Unit Tests erarbeitet wurden und währenddessen laufend der Server um die fertigen Funktionen erweitert wurde.
Danach wurden die Plugins umgesetzt.

Während der gesamten Umsetzung musste laufend das Verständnis über Java und HTTP erweitert werden, um mit der Aufgabenstellung fertig zu werden. 


<h2>Worauf bin ich stolz</h2>

Auf meine Gesamtleistung und konsequente Arbeitshaltung, die sich in der Gesamtanzahl der Arbeitsstunden recht deutlich widerspiegelt.
Weiters auf die komplette Einbindung von Material Design Bootstrap als Front End Tool über Java.


<h2>Was würde ich das nächste mal anders machen?</h2>
Keine "advanced" Benutzeroberfläche - ich konnte dadurch zwar das static plugin eingehend testen, jedoch war es zeitlich gesehen ein außerordentlicher Mehraufwand, wodurch ich am Ende nicht mehr in der Lage war, die letzten wesentlichen Bugs zu analysieren.
