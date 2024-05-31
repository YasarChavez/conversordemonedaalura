package com.chavezyasar;

import java.io.IOException;
import java.util.Scanner;

import com.chavezyasar.models.Moneda;
import com.chavezyasar.utilidades.Consulta;
import com.google.gson.JsonElement;

public class Main {
    public static void main(String[] args) {

        Consulta consulta = new Consulta();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        String listaMonedas = """
                USD,AED,AFN,ALL,AMD,ANG,AOA,ARS,AUD,AWG,AZN,BAM,BBD,BDT,BGN,BHD,BIF,BMD,BND,BOB,BRL,BSD,BTN,BWP,BYN,BZD,CAD,CDF,
                CHF,CLP,CNY,COP,CRC,CUP,CVE,CZK,DJF,DKK,DOP,DZD,EGP,ERN,ETB,EUR,FJD,FKP,FOK,GBP,GEL,GGP,GHS,GIP,GMD,GNF,GTQ,GYD,
                HKD,HNL,HRK,HTG,HUF,IDR,ILS,IMP,INR,IQD,IRR,ISK,JEP,JMD,JOD,JPY,KES,KGS,KHR,KID,KMF,KRW,KWD,KYD,KZT,LAK,LBP,LKR,
                LRD,LSL,LYD,MAD,MDL,MGA,MKD,MMK,MNT,MOP,MRU,MUR,MVR,MWK,MXN,MYR,MZN,NAD,NGN,NIO,NOK,NPR,NZD,OMR,PAB,PEN,PGK,PHP,
                PKR,PLN,PYG,QAR,RON,RSD,RUB,RWF,SAR,SBD,SCR,SDG,SEK,SGD,SHP,SLE,SLL,SOS,SRD,SSP,STN,SYP,SZL,THB,TJS,TMT,TND,TOP,
                TRY,TTD,TVD,TWD,TZS,UAH,UGX,UYU,UZS,VES,VND,VUV,WST,XAF,XCD,XDR,XOF,XPF,YER,ZAR,ZMW,ZWL""";
        System.out.println("Escriba la moneda base:");
        System.out.println(listaMonedas);
        String base = leer.nextLine().toUpperCase();
        System.out.println("Escriba la moneda a destino:");
        System.out.println(listaMonedas.replace(base + ",", ""));
        String cambio = leer.nextLine().toUpperCase();
        double cantidadBase = 0;
        try {
            System.out.println("Escriba la cantidad de " + base + " a convertir en " + cambio);
            cantidadBase = Double.valueOf(leer.nextLine());
        } catch (Exception e) {
            System.out.println("Ocurrió un problema, asegurese de ingresar un numero");
        }
        try {
            if (!(base.equals(cambio))) {
                Moneda moneda = consulta.getBase(base);
                JsonElement temp = moneda.conversion_rates().get(cambio);
                Double rate = temp.getAsDouble() * cantidadBase;

                System.out.println("1 " + base + " : " + moneda.conversion_rates().get(cambio) + " " + cambio);
                System.out.println(cantidadBase + " " + base + " son " + rate + " " + cambio);
            } else {
                System.out.println("Ocurrió un problema, confirme que escribió bien las monedas!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        leer.close();

    }
}