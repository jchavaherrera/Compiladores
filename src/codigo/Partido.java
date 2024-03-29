/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package codigo;

/**
 *
 * @author Asus VivoBook
 */
public class Partido {
    private int outs;
    private boolean[] bases;
    private int entrada;
    private int[] marcador;
    private int equipoBateando;
    
    public Partido(){
        outs = 0;
        bases = new boolean[3];
        entrada = 1;
        marcador = new int[2];
        equipoBateando = 0;
    }
    
    public void AvanzaBase(int avance, int base){
        if(base < bases.length){
            if(bases[base]){
                AvanzaRecursivo(base+1);
            }
            --avance;
            if(avance != 0){
                bases[base] = false;
                AvanzaBase(avance, base+1);
            }
            else{
                bases[base] = true;
            }
        }
        else{
            ++marcador[equipoBateando];
        }
    }
    
    private void AvanzaRecursivo(int base){
        if(base < bases.length){
            if(bases[base]){
                AvanzaRecursivo(base+1);
            }
            else{
                bases[base] = true;
            }
        }
        else{
           ++marcador[equipoBateando];
        }
    }
    
    public void Out(int cantidadOuts){
        for(int i = 0; i < cantidadOuts; ++i)
            ++outs;
        if(outs >= 3){
           outs = 0;
           if(equipoBateando == 1)
               ++entrada;
           equipoBateando = (equipoBateando+1)%2;
           for(int i = 0; i < bases.length; ++i)
               bases[i] = false;
        }
    }
    
    public void Sacrificio(){
        for(int i = bases.length; i > 0; --i){
            System.out.print(i-1);
            System.out.print("\n");
            if(bases[i-1]){
                AvanzaBase(1, i-1);
                bases[i-1] = false;
            }
        }
        Out(1);
    }
    
    public String toString(){
        String str = "Entrada: " + String.valueOf(entrada) + "\n\nMarcador\nEquipo 1: " 
                + String.valueOf(marcador[1])
                + "\nEquipo 2: " +  String.valueOf(marcador[0])
                + "\n\nBase 1: " + ((bases[0]) ?   "Llena":"Vacía") + "\nBase 2: " + 
                ((bases[1]) ?   "Llena":"Vacía") + "\nBase 3: " + ((bases[2]) ?   "Llena":"Vacía")
                + "\nOuts: " + String.valueOf(outs) + "\nEquipo bateando: ";
        if(equipoBateando+1 == 2){
            str = str + "1\n--------------------\n";
        }
        else{
            str = str+ "2\n--------------------\n";
        }
        return str;
    }
}
