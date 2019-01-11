package com.example.robin.angrynerds_wip.data.models.utils;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
//import android.support.annotation.NonNull;

import com.example.robin.angrynerds_wip.R;
import com.example.robin.angrynerds_wip.data.models.tens.Event;
import com.example.robin.angrynerds_wip.data.models.tens.Note;
import com.example.robin.angrynerds_wip.data.models.tens.TEN;
import com.example.robin.angrynerds_wip.data.models.tens.Todo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MockData {

    //Nicht-Statische Liste mit 10 todos, 10 Events und 10 Notes
    //Verwendung mit MockData.tenMockData -> Bitte Nicht
    private ArrayList<TEN> tenMockData = new ArrayList<>();

    public MockData(Activity activity) {

        Note koalaNote = new Note("Interessantes über Koalabären", "Der Koala ist ein " +
                "baumbewohnender Beutelsäuger in Australien. Er wurde von dem Zoologen Georg " +
                "August Goldfuß im Jahre 1817 als Lipurus cinereus erstbeschrieben. Der Koala ist " +
                "neben dem Känguru das am weitesten verbreitete Symbol Australiens.");

        ArrayList<String> koalaTags = new ArrayList<String>();
        koalaTags.add("Bär");
        koalaTags.add("Baum");
        koalaTags.add("Eukalyptus");
        koalaTags.add("Australien");
        koalaNote.setTags(koalaTags);

        koalaNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_koala1));
        koalaNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_koala2));

        tenMockData.add(koalaNote);

        Date visitKoalaDate = new Date(1588590000);
        Date reminder2 = new Date(1588590000 - (1000 * 60 * 60 * 24 * 7));
        ArrayList<Date> visitKoalaReminder = new ArrayList<Date>();
        visitKoalaReminder.add(visitKoalaDate);
        visitKoalaReminder.add(reminder2);
        String address = "Australien, Outback";
        Event koalaEvent = new Event("Koalas besuchen", visitKoalaDate, visitKoalaReminder, address);
        tenMockData.add(koalaEvent);

        Note delfinNote = new Note("Delfine", "Die Delfine oder Delphine gehören zu " +
                "den Zahnwalen und sind somit Säugetiere, die im Wasser leben. Delfine sind die" +
                " vielfältigste und mit rund 40 Arten größte Familie der Wale. Sie sind in allen " +
                "Meeren verbreitet.");

        ArrayList<String> delfinTags = new ArrayList<String>();
        koalaTags.add("Säugetiere");
        koalaTags.add("intelligent");
        koalaTags.add("Meer");
        delfinNote.setTags(delfinTags);

        delfinNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_delfin1));
        delfinNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_delfin2));

        tenMockData.add(delfinNote);


        Task besuchMamaPapa1 = new Task("Küche putzen");
        Task besuchMamaPapa2 = new Task("Bad putzen");
        Task besuchMamaPapa3 = new Task("Zimmer aufräumen");

        ArrayList<Task> besuchMamaPapaTasks = new ArrayList<Task>();
        besuchMamaPapaTasks.add(besuchMamaPapa1);
        besuchMamaPapaTasks.add(besuchMamaPapa2);
        besuchMamaPapaTasks.add(besuchMamaPapa3);

        Todo besuchMamaPapa = new Todo("Besuch von Mama und Papa", "Alles muss sauber und " +
                "aufgeräumt sein!", besuchMamaPapaTasks);
        tenMockData.add(besuchMamaPapa);

        Note klausureingrenzung = new Note("Klausureingrenzung", "Es kommt die folgenden Folien vor: 24-29, 50-71, 83-101");
        ArrayList<String> klausurTags = new ArrayList<String>();
        klausurTags.add("wichtig");
        klausurTags.add("FH");
        klausureingrenzung.setTags(klausurTags);
        tenMockData.add(klausureingrenzung);


        Date konzertBringsDate = new Date(1563879600);
        Date reminder2Brings = new Date(1563879600 - (1000 * 60 * 60 * 24 * 7));
        ArrayList<Date> konzertBringsReminder = new ArrayList<Date>();
        konzertBringsReminder.add(konzertBringsDate);
        konzertBringsReminder.add(reminder2Brings);
        String addressBrings = "Köln, Tanzbrunnen";
        Event konzertBrings = new Event("Konzert Brings", konzertBringsDate, konzertBringsReminder, addressBrings);
        tenMockData.add(konzertBrings);

        Date elternBesuchDate = new Date(1550826000);
        Date reminder2elternBesuch = new Date(1550826000 - (1000 * 60 * 60 * 24 * 7));
        ArrayList<Date> elternBesuchReminder = new ArrayList<Date>();
        elternBesuchReminder.add(elternBesuchDate);
        elternBesuchReminder.add(reminder2elternBesuch);
        String addressElternBesuch = "zuhause";
        Event elternBesuch = new Event("Besuch von Mama und Papa", elternBesuchDate, elternBesuchReminder, addressElternBesuch);
        tenMockData.add(elternBesuch);


        Task küchePutzen1 = new Task("Arbeitsfläche wischen");
        Task küchePutzen2 = new Task("Geschirr spülen");
        Task küchePutzen3 = new Task("Gläser einräumen");

        ArrayList<Task> küchePutzenTasks = new ArrayList<Task>();
        küchePutzenTasks.add(küchePutzen1);
        küchePutzenTasks.add(küchePutzen2);
        küchePutzenTasks.add(küchePutzen3);

        Todo küchePutzen = new Todo("Küche aufräumen", "Das Backen war ein Fiasko",
                küchePutzenTasks);
        tenMockData.add(küchePutzen);


        Task reiseVorbereitung1 = new Task("Wäsche waschen");
        Task reiseVorbereitung2 = new Task("Taschepacken");
        Task reiseVorbereitung3 = new Task("Zeitschaltuhr einstellen");

        ArrayList<Task> reiseVorbereitungTasks = new ArrayList<Task>();
        reiseVorbereitungTasks.add(reiseVorbereitung1);
        reiseVorbereitungTasks.add(reiseVorbereitung2);
        reiseVorbereitungTasks.add(reiseVorbereitung3);

        Todo reiseVorbereitung = new Todo("Vorbereitung auf die Australienreise",
                "",
                reiseVorbereitungTasks);
        tenMockData.add(reiseVorbereitung);

        Note kaenguruNote = new Note("Kängurus","Kängurus gelten als typischer Vertreter der Fauna Australiens.");
        ArrayList<String> kaenguruTags = new ArrayList<String>();
        kaenguruTags.add("Beutel");
        kaenguruTags.add("Sprung");
        kaenguruTags.add("DownUnder");
        kaenguruTags.add("Australien");
        kaenguruNote.setTags(kaenguruTags);

        kaenguruNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_kaenguru1));
        kaenguruNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_kaenguru2));

        tenMockData.add(kaenguruNote);

        Note melbourneNote = new Note("Melbourne", "Melbourne ist die an der " +
                "Südostküste Australiens gelegene Hauptstadt des australischen Bundesstaats " +
                "Victoria. Im Stadtzentrum liegt der moderne Federation-Square-Komplex mit Plätzen," +
                " Bars und Restaurants am Fluss Yarra. Am südlichen Flussufer im Kunstbezirk der" +
                " Stadt befinden sich das Arts Centre Melbourne – ein Zentrum für darstellende" +
                " Künste – und die National Gallery of Victoria, in der Werke von australischen" +
                " Künstlern und Aborigines ausgestellt werden.");
        ArrayList<String> melbourneTags = new ArrayList<String>();
        melbourneTags.add("City");
        melbourneTags.add("Nightlife");
        melbourneTags.add("River");
        melbourneTags.add("Skyline");
        melbourneNote.setTags(melbourneTags);

        melbourneNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_melbourne1));
        //melbourneNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_melbourne2));
        melbourneNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_melbourne3));

        tenMockData.add(melbourneNote);

        Note bambusNote = new Note("Bambus", "Bambus ist eine der zwölf" +
                " Unterfamilien aus der Familie der Süßgräser, der etwa 116 Gattungen zugerechnet" +
                " werden. Die Unterfamilie wird in drei Tribus geteilt, wobei Arundinarieae und" +
                " Bambuseae verholzende Arten beinhalten und Olyreae krautig wachsende Pflanzen.");
        ArrayList<String> bambusTags = new ArrayList<String>();
        bambusTags.add("Bär");
        bambusTags.add("Baum");
        bambusTags.add("Eukalyptus");
        bambusTags.add("Australien");
        bambusNote.setTags(bambusTags);

        bambusNote.getPictures().add(BitmapFactory.decodeResource(activity.getResources(), R.drawable.sample_image_bambus1));

        tenMockData.add(bambusNote);

        Date delfinDate = new Date(1588831200);
        Date reminder2delfin = new Date(1588831200 - (1000 * 60 * 60 * 24 * 7));
        ArrayList<Date> delfinReminder = new ArrayList<Date>();
        delfinReminder.add(delfinDate);
        delfinReminder.add(reminder2delfin);
        String delfinAdresse = "Tin Can Bay";
        Event delfinSchwimmen = new Event("Schwimmen mit Delfinen", delfinDate, delfinReminder, delfinAdresse);
        tenMockData.add(delfinSchwimmen);


        Task klausurVorbereitung1 = new Task("24-29 lernen");
        Task klausurVorbereitung2 = new Task("50-71 lernen");
        Task klausurVorbereitung3 = new Task("83-101 lernen");
        Task klausurVorbereitung4 = new Task("Übungsaufgaben Bilanz");
        Task klausurVorbereitung5 = new Task("Übungsaufgaben GuV");

        ArrayList<Task> klausurVorbereitungTasks = new ArrayList<Task>();
        klausurVorbereitungTasks.add(klausurVorbereitung1);
        klausurVorbereitungTasks.add(klausurVorbereitung2);
        klausurVorbereitungTasks.add(klausurVorbereitung3);
        klausurVorbereitungTasks.add(klausurVorbereitung4);
        klausurVorbereitungTasks.add(klausurVorbereitung5);

        Todo klausurVorbereitung = new Todo("Vorbereitung auf die Rechnungswesenklausur",
                "",
                klausurVorbereitungTasks);
        tenMockData.add(klausurVorbereitung);


        Task appProgrammierung1 = new Task("Anforderungen analysieren", true);
        Task appProgrammierung2 = new Task("Projekt planen", true);
        Task appProgrammierung3 = new Task("App entwerfen", true);
        Task appProgrammierung4 = new Task("App implementieren", true);
        Task appProgrammierung5 = new Task("App testen", true);
        Task appProgrammierung6 = new Task("App übergeben");

        ArrayList<Task> appProgrammierungTasks = new ArrayList<Task>();
        appProgrammierungTasks.add(appProgrammierung1);
        appProgrammierungTasks.add(appProgrammierung2);
        appProgrammierungTasks.add(appProgrammierung3);
        appProgrammierungTasks.add(appProgrammierung4);
        appProgrammierungTasks.add(appProgrammierung5);
        appProgrammierungTasks.add(appProgrammierung6);

        Todo appProgrammierung = new Todo("App Programmierung",
                "Grobes Schema einer Softwareentwickung",
                appProgrammierungTasks);
        tenMockData.add(appProgrammierung);
    }

    public ArrayList<TEN> getMockData() {
        return this.tenMockData;
    }

}