package com.example.toukapp.services;

import com.example.toukapp.dtos.RoomRequest;
import com.example.toukapp.dtos.RoomResponse;
import com.example.toukapp.entity.Room;
import com.example.toukapp.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RoomServiceImplementation implements RoomService{
    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImplementation(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomResponse> getAll() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
                .map(roomEntity -> new RoomResponse(roomEntity.getRoomId(), roomEntity.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public RoomResponse getRoom(int roomId){
        List<RoomResponse> list = getAll();
        if(roomRepository.existsById(roomId)){
            return list.stream().filter(room -> room.getRoomId() == roomId).findAny().get();
        }
        return null;
    }

    @Override
    public void addRoom(RoomRequest roomRequest){
        Room roomEntity = new Room();
        roomEntity.setName(roomRequest.getName());
        roomRepository.save(roomEntity);
    }

    @Override
    public void deleteRoom(int roomId){
        if(roomRepository.existsById(roomId)){
            roomRepository.deleteById(roomId);
        }
    }

    @Override
    public void updateRoom(int roomId, RoomRequest roomRequest){
        if(roomRepository.existsById(roomId)){
            Room roomEntity = new Room();
            roomEntity.setName(roomRequest.getName());
            roomRepository.save(roomEntity);
        }
    }
}
