package util;

public class Translit {
    public static String cyr2lat(char ch){
        switch (ch){
            case 'А': return "A";
            case 'Б': return "B";
            case 'В': return "V";
            case 'Г': return "G";
            case 'Д': return "D";
            case 'Е': return "E";
            case 'Ё': return "JO";
            case 'Ж': return "ZH";
            case 'З': return "Z";
            case 'И': return "I";
            case 'Й': return "Y";
            case 'К': return "K";
            case 'Л': return "L";
            case 'М': return "M";
            case 'Н': return "N";
            case 'О': return "O";
            case 'П': return "P";
            case 'Р': return "R";
            case 'С': return "S";
            case 'Т': return "T";
            case 'У': return "U";
            case 'Ф': return "F";
            case 'Х': return "KH";
            case 'Ц': return "TS";
            case 'Ч': return "CH";
            case 'Ш': return "SH";
            case 'Щ': return "SHCH";
            case 'Ъ': return ""/*"HH"*/;
            case 'Ы': return "Y";
            case 'Ь': return "'"/*"JH"*/;
            case 'Э': return "E";
            case 'Ю': return "JU";
            case 'Я': return "JA";

            case 'а': return "a";
            case 'б': return "b";
            case 'в': return "v";
            case 'г': return "g";
            case 'д': return "d";
            case 'е': return "e";
            case 'ё': return "jo";
            case 'ж': return "zh";
            case 'з': return "z";
            case 'и': return "i";
            case 'й': return "y";
            case 'к': return "k";
            case 'л': return "l";
            case 'м': return "m";
            case 'н': return "n";
            case 'о': return "o";
            case 'п': return "p";
            case 'р': return "r";
            case 'с': return "s";
            case 'т': return "t";
            case 'у': return "u";
            case 'ф': return "f";
            case 'х': return "kh";
            case 'ц': return "ts";
            case 'ч': return "ch";
            case 'ш': return "sh";
            case 'щ': return "shch";
            case 'ъ': return ""/*"hh"*/;
            case 'ы': return "y";
            case 'ь': return "'"/*"jh"*/;
            case 'э': return "e";
            case 'ю': return "ju";
            case 'я': return "ja";
            default: return String.valueOf(ch);
        }
    }

    public static String cyr2lat(String s){
        StringBuilder sb = new StringBuilder(s.length()*2);
        for(char ch : s.toCharArray()){
            sb.append(cyr2lat(ch));
        }
        return sb.toString();
    }
}