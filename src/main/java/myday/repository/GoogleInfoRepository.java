package myday.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import myday.model.GoogleInfo;
import myday.model.GoogleInfoKey;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface GoogleInfoRepository extends CrudRepository<GoogleInfo, GoogleInfoKey> {
  // nothing to do here
}