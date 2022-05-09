package com.demo.kalah.repo.Impl;

import com.demo.kalah.Exception.NoSuchGameExistsException;
import com.demo.kalah.dto.GameServiceRequest;
import com.demo.kalah.repo.KalahGameRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KalahGameRepositoryImplTest {

    private KalahGameRepository kalahGameRepository = new KalahGameRepositoryImpl();;



    @Test()
    void getGameByIdExceptionThrown() {
        NoSuchGameExistsException exception = assertThrows(
                NoSuchGameExistsException.class,
                () -> kalahGameRepository.getGameById(new GameServiceRequest())
        );
        assertTrue(exception instanceof NoSuchGameExistsException);
    }


    @Test
    void createKalahGame() {
    }

    @Test
    void saveKalahGameState() {
    }

    @Test
    void purgeStore() {
    }
}