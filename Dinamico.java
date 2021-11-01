import java.io.*;
import java.util.Scanner;

public class Dinamico {
    static void Firstfit(int blockSize[], int p, int processSize[], int s) {
        int allocate[] = new int[s];
        for (int i = 0; i < allocate.length; i++)
            allocate[i] = -1;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < p; j++) {
                if (blockSize[j] >= processSize[i]) {
                    allocate[i] = j;
                    blockSize[j] -= processSize[i];
                    break;
                }
            }
        }
        System.out.println("\nProcess Number\tProcess Size\tBlock Number.");
        for (int i = 0; i < s; i++) {
            System.out.print(" " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocate[i] != -1)
                System.out.print(allocate[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }

    static void BestFit(int flags, int segments) throws NumberFormatException, IOException {
        int flag[] = new int[flags];
        int m_segments[] = new int[segments];
        int i, sr, memory;
        int sp, loc = 0;
        int cntrl = 1000;
        DataInputStream in = new DataInputStream(System.in);
        System.out.println("Enter the no of memory segments\n");
        memory = Integer.parseInt(in.readLine());
        System.out.println("Enter the size of memory segments\n");
        for (i = 0; i < memory; i++)
            m_segments[i] = Integer.parseInt(in.readLine());
        for (i = 0; i < memory; i++)
            flag[i] = 0;
        System.out.println("Before best fit allocation\n");
        System.out.println("\nIndex\t\tMemory Segments\n");
        for (i = 0; i < memory; i++)

            System.out.println((i + 1) + "\t\t" + m_segments[i]);
        System.out.println("\nEnter the space requirement for new process\n");
        sr = Integer.parseInt(in.readLine());
        for (i = 0; i < memory; i++) {
            if (flag[i] == 0) {
                sp = m_segments[i];

                if (sr <= sp) {
                    if (cntrl > sp) {
                        cntrl = sp;
                        loc = i;
                    }
                }
            }
        }
        if (cntrl == 0)
            System.out.println("\n Space not available");
        else {
            m_segments[loc] = sr;
            flag[loc] = 1;
        }
        System.out.println("\nAfter Bestfit Allocation\n");
        System.out.println("Index \t\t Memory Segment\n");
        for (i = 0; i < memory; i++) {
            System.out.println((i + 1) + "\t\t" + m_segments[i]);
        }
        System.out.println("\n The process allocated to the memory segments " + (loc + 1));
    }

    static void BestFitExtended(int b_size[], int m, int p_size[], int n)
    {
        int allocate[] = new int[n];
        for ( int i=0; i< allocate.length; i++)
            allocate[i] =-1;
        for (int x=0; x<n; x++)
        {
            int wstIdx = -1;
            for (int j=0; j<m; j++)
            { 
                if (b_size[j] >= p_size[x]) 
                { 
                    if (wstIdx == -1) 
                        wstIdx = j; 
                    else if (b_size[wstIdx] < b_size[j]) 
                        wstIdx = j;
                }
                if (wstIdx != -1) 
                {
                    allocate[x] = wstIdx; 
                    b_size[wstIdx] -= p_size[x]; 
                }  
                System.out.println("\nProcess Number \tProcess Size\tBlock Number "); 
                for (int i = 0; i < n; i++) 
                { 
                    System.out.print(" " + (i+1) + "\t\t" + p_size[i] + "\t\t"); 
                    if (allocate[i] != -1) 
                        System.out.print(allocate[i] + 1);
                    else{
                        System.out.print("Not Allocated");
                        System.out.println(); 
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Metodos");
        System.out.println("1.- First Fit");
        System.out.println("2.- Best Fit");
        System.out.println("3.- Best Fit Extended");
        System.out.print("Opcion: ");
        int opcion = Integer.parseInt(scanner.nextLine());
        switch(opcion)
        {
            case 1:
                System.out.println("Diagrama de memoria tipo First Fit");
                System.out.print("Tamaño de la memoria: ");
                int bloque = Integer.parseInt(scanner.nextLine());
                int bloques[] = new int[bloque];
                for(int i=0;i<bloque;i++)
                {
                    System.out.print("Espacio de memoria #"+(i+1)+": ");
                    bloques[i] = Integer.parseInt(scanner.nextLine());
                }
                System.out.print("Tamaño del proceso: ");
                int proceso = Integer.parseInt(scanner.nextLine());
                int procesos[] = new int[proceso];
                for(int i=0;i<proceso;i++)
                {
                    System.out.print("Espacio de memoria #"+(i+1)+": ");
                    procesos[i] = Integer.parseInt(scanner.nextLine());
                }
                Firstfit(bloques, bloque, procesos, proceso);
            break;
            case 2:
                System.out.println("Diagrama de memoria tipo Best Fit");
                System.out.print("Banderas: ");
                int flags = Integer.parseInt(scanner.nextLine());
                System.out.print("Segmentos: ");
                int segments = Integer.parseInt(scanner.nextLine());
                BestFit(flags, segments);
            break;
            case 3:
                System.out.println("Diagrama de memoria tipo Best Fit Extended");
                System.out.println("Diagrama de memoria tipo First Fit");
                System.out.print("Tamaño de la memoria: ");
                int m = Integer.parseInt(scanner.nextLine());
                int b_size[] = new int[m];
                for(int i=0;i<m;i++)
                {
                    System.out.print("Espacio de memoria #"+(i+1)+": ");
                    b_size[i] = Integer.parseInt(scanner.nextLine());
                }
                System.out.print("Tamaño del proceso: ");
                int n = Integer.parseInt(scanner.nextLine());
                int p_size[] = new int[n];
                for(int i=0;i<n;i++)
                {
                    System.out.print("Espacio de memoria #"+(i+1)+": ");
                    p_size[i] = Integer.parseInt(scanner.nextLine());
                }
                BestFitExtended(b_size, m, p_size, n);
            break;
            default:
            System.out.print("Opcion incorrecta");
            scanner.nextLine();
            break;
        }
        scanner.close();
    }
}