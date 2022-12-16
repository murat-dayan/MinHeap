package minheap;

import java.util.Scanner;

// MURAT DAYAN 
// 02190201013

public class MinHeap {

    // Dizi min heap mi değil mi kontrol eder
    public static boolean MinHeapKontrolEt(int[] alinanDizi, int i) {
        // `i' bir yaprak düğümüyse, her yaprak düğümü bir yığın olduğundan true değerini döndürür
        if (2 * i + 2 > alinanDizi.length) {
            return true;
        }

        // `i' bir iç düğüm ise
        // sol çocuğun bir yığın olup olmadığının kontrolü
        boolean sol = (alinanDizi[i] <= alinanDizi[2 * i + 1]) && MinHeapKontrolEt(alinanDizi, 2 * i + 1);

        // sağ cocuk yığın mı değil mi kontrolü
        boolean sag = (2 * i + 2 == alinanDizi.length)
                || (alinanDizi[i] <= alinanDizi[2 * i + 2] && MinHeapKontrolEt(alinanDizi, 2 * i + 2));

        // sag ve sol cocuklar heap ise true değeri döndürür
        return sol && sag;
    }

    public static void minHeapOlustur(int[] dizi) {
        for (int i = (dizi.length - 1) / 2; i >= 0; i--) {
            minHeapCevir(dizi, dizi.length, i);
        }
    }

    public static void minHeapCevir(int[] dizi, int n, int i) {
        int anaDugum = i;
        int sol_cocuk = (2 * i) + 1;
        int sag_cocuk = (2 * i) + 2;
        if (sol_cocuk < dizi.length && dizi[anaDugum] > dizi[sol_cocuk]) {
            anaDugum = sol_cocuk;
        }
        if (sag_cocuk < dizi.length && dizi[anaDugum] > dizi[sag_cocuk]) {
            anaDugum = sag_cocuk;
        }
        if (anaDugum != i) {
            int temp = dizi[anaDugum];
            dizi[anaDugum] = dizi[i];
            dizi[i] = temp;
            minHeapCevir(dizi, n, anaDugum);
        }
    }

    public static void print(int[] dizi) {
        System.out.print("min heap hali ->>    [ ");
        for (int i = 0; i < dizi.length; i++) {
            System.out.print(dizi[i] + " ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        Scanner girdi = new Scanner(System.in);
        System.out.print("Dizi elemanların virgülle ayırarak giriniz	  : ");
        String diziElemanlari = girdi.next();
        
        String[] ayrilmisDiziElemanlari = diziElemanlari.split(",");
        
        int[] alinanDizi = new int[ayrilmisDiziElemanlari.length];
        
        for (int i = 0; i < ayrilmisDiziElemanlari.length; i++) {
            alinanDizi[i] = Integer.parseInt(ayrilmisDiziElemanlari[i]);
        }
        int index = 0;

        if (MinHeapKontrolEt(alinanDizi, index)) {
            System.out.println("evet min heaptir");
        } else {
            System.out.println("min heap değildir");
            minHeapOlustur(alinanDizi);
            print(alinanDizi);
        }

    }
}
