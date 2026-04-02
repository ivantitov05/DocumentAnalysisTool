package MephiPackage.utils;

import MephiPackage.objects.Curse;
import MephiPackage.objects.Mission;
import MephiPackage.objects.Sorcerer;
import MephiPackage.objects.Technique;

import java.util.List;

public class MissionPrinter {
    public static void print(Mission mission) {
        if (mission == null) {
            System.out.println("Миссия не загружена");
            return;
        }

        System.out.println("Данные о прочитанной миссии:\n");
        printBasicInfo(mission);
        printCurseInfo(mission.getCurses());
        printSorcerersInfo(mission.getSorcerers());
        printTechniquesInfo(mission.getTechniques());
    }


    private static void printBasicInfo(Mission mission) {
        System.out.println("Основные данные:");
        System.out.println("ID миссии: " + safeString(mission.getMissionId()));
        System.out.println("Дата: " + safeString(mission.getDate()));
        System.out.println("Локация: " + safeString(mission.getLocation()));
        System.out.println("Результат: " + safeString(mission.getOutcome()));
        System.out.println("Ущерб: " + mission.getDamageCost());
    }

    private static void printCurseInfo(List<Curse> curses) {
        System.out.println("Проклятья :");
        if (curses.isEmpty()) {
            System.out.println("Нет проклятий");
            return;
        }

        for (int i = 0; i < curses.size(); i++) {
            Curse s = curses.get(i);
            System.out.println("Проклятье: " + safeString(s.getName()));
        }
    }

    private static void printSorcerersInfo(List<Sorcerer> sorcerers) {
        System.out.println("Волшебники на задании:");

        if (sorcerers.isEmpty()) {
            System.out.println("Нет волшебников");
            return;
        }

        for (int i = 0; i < sorcerers.size(); i++) {
            Sorcerer s = sorcerers.get(i);
            System.out.println("  " + (i+1) + ". " + safeString(s.getName()) +
                    " (ранг: " + safeString(s.getRank()) + ")");
        }
    }

    private static void printTechniquesInfo(List<Technique> techniques) {
        System.out.println("Техники: ");

        if (techniques == null || techniques.isEmpty()) {
            System.out.println("Нет техник");
            return;
        }

        for (int i = 0; i < techniques.size(); i++) {
            Technique t = techniques.get(i);
            System.out.println("  " + (i+1) + ". " + safeString(t.getName()));
            System.out.println("Тип: " + safeString(t.getType()));
            System.out.println("Владелец: " + safeString(t.getOwner()));
            System.out.println("Урон: " + t.getDamage());

        }
    }

    private static String safeString(String str) {
        if (str != null){
            return str;
        }
        else{
            return "-";
        }
    }
}