package ec.edu.epn.poo.lab3;

import java.util.Locale;
import java.util.Scanner;

public class CajaRegistradora {

	public CajaRegistradora() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        sc.useLocale(Locale.US);
        final double IVA = 0.15;
 
        // Catálogo en arreglos paralelos (solución provisional: ver Semana 4)
        String[] nombres = {"Cuaderno", "Esfero", "Borrador", "Regla", "Carpeta"};
        double[] precios = {2.50, 0.75, 0.40, 1.20, 3.10};
        int[] stock = {10, 50, 30, 0, 8};
        int[] carrito = new int[nombres.length];   // unidades compradas
 
        int opcion;
        do {
            System.out.println();
            System.out.println("=== CAJA REGISTRADORA ===");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Agregar producto");
            System.out.println("3. Ver recibo");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            // TODO 1:
            while(!sc.hasNextInt()) {
            	System.out.println("Debe ingresar un número entero: ");
            	sc.next();
            	System.out.print("Opción: "); 
            }		
            opcion = sc.nextInt();
 
            switch (opcion) {
                case 1 -> {
                    // TODO 2:
                	for(int i = 0; i < nombres.length; i++) {
                		System.out.printf("%d. %-9s $%5.2f Stock: %d%n", i+1, nombres[i], precios[i], stock[i]);
                		
                	}
                }
                case 2 -> {
                    // TODO 3: 
                	int codigo;
                    do {
                        System.out.print("Código (1 a " + nombres.length + "): ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Debe ingresar un número entero.");
                            sc.next();
                            System.out.print("Código (1 a " + nombres.length + "): ");
                        }
                        codigo = sc.nextInt();
                        if (codigo < 1 || codigo > nombres.length) {
                            System.out.println("Código fuera de rango.");
                        }
                    } while (codigo < 1 || codigo > nombres.length);

                    // TODO 4: 
                    int cantidad;
                    do {
                        System.out.print("Cantidad (> 0): ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Debe ingresar un número entero.");
                            sc.next();
                            System.out.print("Cantidad (> 0): ");
                        }
                        cantidad = sc.nextInt();
                        if (cantidad <= 0) {
                            System.out.println("La cantidad debe ser mayor a 0.");
                        }
                    } while (cantidad <= 0);

                    if (cantidad <= stock[codigo - 1]) {
                        carrito[codigo - 1] += cantidad;
                        stock[codigo - 1] -= cantidad;
                        System.out.println("Agregado: " + cantidad + " x " + nombres[codigo - 1]);
                    } else {
                        System.out.println("Stock insuficiente (disponible: " + stock[codigo - 1] + ")");
                    }
                }
                case 3 -> {
                    // TODO 5:
                	 double subtotal = 0;
                     System.out.println("--- RECIBO ---");
                     for (int i = 0; i < nombres.length; i++) {
                         if (carrito[i] > 0) {
                             double linea = precios[i] * carrito[i];
                             System.out.printf("%-9s %3d x %5.2f = %6.2f%n",
                                     nombres[i], carrito[i], precios[i], linea);
                             subtotal += linea;
                         }
                     }
                    // TODO 6:
                     double descuento = 0;
                     if (subtotal >= 10) {
                         descuento = subtotal * 0.10;
                     } else if (subtotal >= 5) {
                         descuento = subtotal * 0.05;
                     }
                     double base = subtotal - descuento;
                     double iva = base * IVA;
                     double total = base + iva;

                     System.out.printf("Subtotal:  %8.2f%n", subtotal);
                     System.out.printf("Descuento: %8.2f%n", descuento);
                     System.out.printf("IVA 15%%:   %8.2f%n", iva);
                     System.out.printf("TOTAL:     %8.2f%n", total);
                }
                case 0 -> System.out.println("Gracias por su compra.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
        sc.close();

	}

}
