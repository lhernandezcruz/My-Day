package myday.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import myday.model.Rating;
import myday.model.RatingKey;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface RatingsRepository extends CrudRepository<Rating, RatingKey> {
  // nothing to do here
}