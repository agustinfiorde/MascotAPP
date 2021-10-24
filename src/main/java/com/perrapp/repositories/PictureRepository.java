package com.perrapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perrapp.entities.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, String> {

}
