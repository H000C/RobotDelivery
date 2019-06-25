/*
 * RecipientRespository interface for Create Read Update Delete
 * 
 * Created by: Haochen Liu
 */

package springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.model.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, String> {

}