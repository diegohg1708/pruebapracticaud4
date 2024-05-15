package org.iesvdm.reservamesa;

//import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReservaMesaTest {

    @Test
    void failTest() {fail("TEST SIN IMPLEMENTAR");}


    private ReservaMesa reservaMesa;

    @BeforeEach
    void setUp() {
        reservaMesa = new ReservaMesa();
    }

    @Test
    void testRellenarMesas() {
        reservaMesa.setTamanioMesa(5);
        int[] mesas = new int[10];
        reservaMesa.setMesas(mesas);

        reservaMesa.rellenarMesas();

        for (int mesa : reservaMesa.getMesas()) {
            assertThat(mesa).isBetween(1, 5);
        }
    }

    @Test
    void testBuscarPrimeraMesaVacia() {
        int[] mesas = {1, 2, 0, 4, 5};
        reservaMesa.setMesas(mesas);

        int mesaVacia = reservaMesa.buscarPrimeraMesaVacia();

        assertThat(mesaVacia).isEqualTo(2);
    }

    @Test
    void testBuscarMesaParaCompartir() {
        reservaMesa.setTamanioMesa(5);
        int[] mesas = {1, 2, 3, 4, 5};
        reservaMesa.setMesas(mesas);

        int mesaParaCompartir = reservaMesa.buscarMesaParaCompartir(2);

        assertThat(mesaParaCompartir).isEqualTo(0);
    }

    @Test
    void testBuscarMesaCompartirMasCercaDe() {
        reservaMesa.setTamanioMesa(5);
        int[] mesas = {1, 2, 3, 4, 0};
        reservaMesa.setMesas(mesas);

        int mesaCompartirMasCerca = reservaMesa.buscarMesaCompartirMasCercaDe(2, 2);

        assertThat(mesaCompartirMasCerca).isEqualTo(1);
    }

    @Test
    void testBuscarMesaCompartirMasAlejadaDe() {
        reservaMesa.setTamanioMesa(5);
        int[] mesas = {1, 2, 3, 4, 0};
        reservaMesa.setMesas(mesas);

        int mesaCompartirMasAlejada = reservaMesa.buscarMesaCompartirMasAlejadaDe(2, 2);

        assertThat(mesaCompartirMasAlejada).isEqualTo(4);
    }

    @Test
    void testBuscarCompartirNMesasConsecutivas() {
        reservaMesa.setTamanioMesa(5);
        int[] mesas = {1, 2, 0, 0, 0, 4, 5};
        reservaMesa.setMesas(mesas);

        int mesaConsecutivas = reservaMesa.buscarCompartirNMesasConsecutivas(3, 3);

        assertThat(mesaConsecutivas).isEqualTo(2);
    }

    @Test
    void testComensalesTotales() {
        int[] mesas = {1, 2, 3, 4, 5};
        reservaMesa.setMesas(mesas);

        int comensalesTotales = reservaMesa.comensalesTotales();

        assertThat(comensalesTotales).isEqualTo(15);
    }
}
