package com.springboot.rent_read_springsecurity.mapper;

import com.springboot.rent_read_springsecurity.dto.RentalDto;
import com.springboot.rent_read_springsecurity.entity.Rental;

public class RentalMapper {

    public static Rental mapToRental(RentalDto rentalDto){
        Rental rental = new Rental(
            rentalDto.getRentalId(),
            rentalDto.getUser(),
            rentalDto.getBook(),
            rentalDto.getRentedAt(),
            rentalDto.getReturnedAt()
        );
        return rental;
    }

    public static RentalDto mapToRentalDto(Rental rental){
        RentalDto rentalDto = new RentalDto(
            rental.getRentalId(),
            rental.getUser(),
            rental.getBook(),
            rental.getRentedAt(),
            rental.getReturnedAt()
        );
        return rentalDto;
    }
}
