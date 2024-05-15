package org.iesvdm.booking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BookingDAOTest {

    private BookingDAO bookingDAO;
    private Map<String, BookingRequest> bookings;

    @BeforeEach
    public void setup() {
        bookings = new HashMap<>();
        bookingDAO = new BookingDAO(bookings);
    }

    /**
     * Crea 2 peticiones de reserva (BookingRequest)
     * agrégalas a las reservas (bookings) con la que
     * construyes el objeto BookingDAO bajo testeo.
     * Comprueba que cuando invocas bookingDAO.getAllBookingRequest
     * obtienes las 2 peticiones.
     */
    @Test
    void  getAllBookingRequestsTest() {
        BookingRequest request1 = new BookingRequest
                ("Carlos", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7,7), 2, true);

        BookingRequest request2 = new BookingRequest
                ("Alicia", LocalDate.of(2024, 2, 3), LocalDate.of(2024, 2,8), 2, true);

        bookings.put(toString(), request1);
        bookings.put(toString(), request2);
        bookings.put(UUID.randomUUID().toString(), request1);
        bookings.put(UUID.randomUUID().toString(), request2);

        Collection<BookingRequest> allRequests = bookingDAO.getAllBookingRequests();

        assertEquals(2, allRequests.size());
        assertTrue(allRequests.contains(request1));
        assertTrue(allRequests.contains(request2));


    }

    /**
     * Crea 2 peticiones de reserva (BookingRequest)
     * agrégalas a las reservas mediante bookingDAO.save.
     * Comprueba que cuando invocas bookingDAO.getAllUUIDs
     * obtienes las UUIDs de las 2 peticiones guardadas.
     */
    @Test
    void getAllUUIDsTest() {
        BookingRequest request1 = new BookingRequest("Carlos", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7,7), 2, true);
        BookingRequest request2 = new BookingRequest("Alicia", LocalDate.of(2024, 2, 3), LocalDate.of(2024, 2,8), 2, true);

        String uuid1 = bookingDAO.save(request1);
        String uuid2 = bookingDAO.save(request2);

        Set<String> allUUIDs = bookingDAO.getAllUUIDs();

        assertEquals(2, allUUIDs.size());
        assertTrue(allUUIDs.contains(uuid1));
        assertTrue(allUUIDs.contains(uuid2));
    }


    /**
     * Crea 2 peticiones de reserva (BookingRequest)
     * agrégalas a las reservas mediante bookingDAO.save.
     * Comprueba que cuando invocas bookingDAO.get con el UUID
     * obtienes las respectivas 2 peticiones guardadas.
     */
    @Test
    void getTest() {
        BookingRequest request1 = new BookingRequest("Carlos", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7,7), 2, true);
        BookingRequest request2 = new BookingRequest("Alicia", LocalDate.of(2024, 2, 3), LocalDate.of(2024, 2,8), 2, true);

        String uuid1 = bookingDAO.save(request1);
        String uuid2 = bookingDAO.save(request2);

        BookingRequest retrievedRequest1 = bookingDAO.get(uuid1);
        BookingRequest retrievedRequest2 = bookingDAO.get(uuid2);

        assertEquals(request1, retrievedRequest1);
        assertEquals(request2, retrievedRequest2);

    }

    /**
     * Crea 2 peticiones de reserva (BookingRequest)
     * agrégalas a las reservas mediante bookingDAO.save.
     * A continuación, borra la primera y comprueba
     * que se mantiene 1 reserva, la segunda guardada.
     */
    @Test
    void deleteTest() {
        BookingRequest request1 = new BookingRequest("Carlos", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7,7), 2, true);
        BookingRequest request2 = new BookingRequest("Alicia", LocalDate.of(2024, 2, 3), LocalDate.of(2024, 2,8), 2, true);

        String uuid1 = bookingDAO.save(request1);
        String uuid2 = bookingDAO.save(request2);

        bookingDAO.delete(uuid1);

        assertNull(bookingDAO.get(uuid1));
        assertNotNull(bookingDAO.get(uuid2));
        assertEquals(1, bookingDAO.totalBookings());
    }

    /**
     * Guarda 2 veces la misma petición de reserva (BookingRequest)
     * y demuestra que en la colección de bookings están repetidas
     * pero con UUID diferente
     *
     */
    @Test
    void saveTwiceSameBookingRequestTest() {
        BookingRequest request = new BookingRequest("Carlos", LocalDate.of(2024, 7, 1), LocalDate.of(2024, 7,7), 2, true);

        String uuid1 = bookingDAO.save(request);
        String uuid2 = bookingDAO.save(request);

        assertNotEquals(uuid1, uuid2);

        BookingRequest retrievedRequest1 = bookingDAO.get(uuid1);
        BookingRequest retrievedRequest2 = bookingDAO.get(uuid2);

        assertEquals(request, retrievedRequest1);
        assertEquals(request, retrievedRequest2);
        assertEquals(2, bookingDAO.totalBookings());
    }

}