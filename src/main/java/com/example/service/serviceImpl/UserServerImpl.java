package com.example.service.serviceImpl;



import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.UpdateMemberRequest;
import com.example.dto.requestDto.UpdateUserRequest;
import com.example.dto.searchRequest.SearchAdmin;
import com.example.dto.searchRequest.SearchMember;

import com.example.dto.responseDto.UserResponse;
import com.example.exception.ConflictException;
import com.example.util.enumData.Role;
import com.example.entity.User;
import com.example.exception.NotFoundException;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServerImpl implements UserService {
    private final UserRepository userRepository;
    private final EntityManager em;
    private final PasswordEncoder passwordEncoder;
    @Override
    public List<UserResponse> getAllMember() {
        List<User> users = userRepository.findAllMember();
        List<UserResponse> members = new ArrayList<>();
        for (User user:users) {
            UserResponse member = UserResponse.makeUser(user);
            members.add(member);
        }
        return members;
    }
    @Override
    public UserResponse getMemberById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("this user with id [%s] not exist",id)));
        return UserResponse.makeUser(user);
    }

    @Override
    public void updateMember(Long id, UpdateMemberRequest request) {

        User user = userRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("this user with id [%s] not exist",id)));

        if(!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())){
            throw  new ConflictException(String.format("this email is already exist ( [%s] ) ",request.getEmail()));
        }
        if(!user.getUsername().equals(request.getUserName()) && userRepository.existsByUserName(request.getUserName())){
            throw  new ConflictException(String.format("this userName is already exist ( [%s] ) ",request.getUserName()));
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRegion(request.getRegion());
        user.setJob(request.getJob());
        user.setUniversityOrCompany(request.getUniversityOrCompany());
        user.setOffice(request.getOffice());
        user.setImage(request.getImage());

        userRepository.save(user);

    }

    @Override
    public void updateUser(Long id, UpdateUserRequest request) {

        User user = userRepository.findById(id).orElseThrow(()->
                new NotFoundException(String.format("this user with id [%s] not exist",id)));
        if(!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())){
            throw  new ConflictException(String.format("this email is already exist ( [%s] ) ",request.getEmail()));
        }
        if(!user.getUsername().equals(request.getUserName()) && userRepository.existsByUserName(request.getUserName())){
            throw  new ConflictException(String.format("this userName is already exist ( [%s] ) ",request.getUserName()));
        }
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRegion(request.getRegion());
        user.setJob(request.getJob());
        user.setUniversityOrCompany(request.getUniversityOrCompany());
        user.setOffice(request.getOffice());
        ///user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setImage(request.getImage());
        user.setPost(request.getPost());
        user.setRole(request.getRole());

        userRepository.save(user);

    }

    @Override
    public UserResponse getConnectedUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<User> user = userRepository.findByUserName(currentUserName);
        if (user.isPresent()) {
            return UserResponse.makeUser(user.get());
        }else throw new RuntimeException("mafamech User *************");
    }
    @Override
    public void deleteMember(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public void enableMember(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        Calendar today = Calendar.getInstance();
        today.add(Calendar.YEAR,1);
        user.setExpiresAt(today.toInstant());
        userRepository.save(user);
    }
    @Override
    public List<UserResponse> getMemberByFilter(SearchMember serachUserDTO) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> root = criteriaQuery.from(User.class);
        if (serachUserDTO.getFirstName() != null){
            Predicate firstNamePredicate = criteriaBuilder
                    .like(root.get("firstName"),"%"+serachUserDTO.getFirstName()+"%");
            predicates.add(firstNamePredicate);
        }
        if (serachUserDTO.getLastName() != null){
            Predicate lastNamePredicate = criteriaBuilder
                    .like(root.get("lastName"),"%"+serachUserDTO.getLastName()+"%");
            predicates.add(lastNamePredicate);
        }
        if (serachUserDTO.getGender() != null){
            Predicate genderPredicate = criteriaBuilder
                    .equal(root.get("gender"),serachUserDTO.getGender());
            predicates.add(genderPredicate);
        }
        if (serachUserDTO.getRegion() != null){
            Predicate regionPredicate = criteriaBuilder
                    .like(root.get("region"),"%"+serachUserDTO.getRegion()+"%");
            predicates.add(regionPredicate);
        }
        if (serachUserDTO.getJob() != null){
            Predicate jobPredicate = criteriaBuilder
                    .like(root.get("job"),"%"+serachUserDTO.getJob()+"%");
            predicates.add(jobPredicate);
        }
        if (serachUserDTO.getUniversityOrCompany() != null){
            Predicate universityOrCompanyPredicate = criteriaBuilder
                    .like(root.get("universityOrCompany"),"%"+serachUserDTO.getUniversityOrCompany()+"%");
            predicates.add(universityOrCompanyPredicate);
        }
        if (serachUserDTO.getPost() != null){
            Predicate postPredicate = criteriaBuilder
                    .equal(root.get("post"),serachUserDTO.getPost());
            predicates.add(postPredicate);
        }
        if (serachUserDTO.getOffice() != null){
            Predicate officePredicate = criteriaBuilder
                    .equal(root.get("office"),serachUserDTO.getOffice());
            predicates.add(officePredicate);
        }
        Predicate expiresPredicate = criteriaBuilder.greaterThan(root.get("expiresAt"), Instant.now());
        predicates.add(expiresPredicate);
        Predicate rolePredicate = criteriaBuilder.equal(root.get("role"), Role.MEMBER);
        predicates.add(rolePredicate);



        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        List<User> users = query.getResultList();
        List<UserResponse> userDto = new ArrayList<>();
        for (User user:users) {
            UserResponse member = UserResponse.makeUser(user);
            userDto.add(member);
        }
        return userDto;
    }
    @Override
    public List<UserResponse> getAllUserByFilter(SearchAdmin searchAdmin) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> root = criteriaQuery.from(User.class);
        if (searchAdmin.getFirstName() != null){
            Predicate firstNamePredicate = criteriaBuilder
                    .like(root.get("firstName"),"%"+searchAdmin.getFirstName()+"%");
            predicates.add(firstNamePredicate);
        }
        if (searchAdmin.getLastName() != null){
            Predicate lastNamePredicate = criteriaBuilder
                    .like(root.get("lastName"),"%"+searchAdmin.getLastName()+"%");
            predicates.add(lastNamePredicate);
        }
        if (searchAdmin.getGender() != null){
            Predicate genderPredicate = criteriaBuilder
                    .equal(root.get("gender"),searchAdmin.getGender());
            predicates.add(genderPredicate);
        }
        if (searchAdmin.getRegion() != null){
            Predicate regionPredicate = criteriaBuilder
                    .like(root.get("region"),"%"+searchAdmin.getRegion()+"%");
            predicates.add(regionPredicate);
        }
        if (searchAdmin.getJob() != null){
            Predicate jobPredicate = criteriaBuilder
                    .like(root.get("job"),"%"+searchAdmin.getJob()+"%");
            predicates.add(jobPredicate);
        }
        if (searchAdmin.getUniversityOrCompany() != null){
            Predicate universityOrCompanyPredicate = criteriaBuilder
                    .like(root.get("universityOrCompany"),"%"+searchAdmin.getUniversityOrCompany()+"%");
            predicates.add(universityOrCompanyPredicate);
        }
        if (searchAdmin.getPost() != null){
            Predicate postPredicate = criteriaBuilder
                    .equal(root.get("post"),searchAdmin.getPost());
            predicates.add(postPredicate);
        }
        if (searchAdmin.getOffice() != null){
            Predicate officePredicate = criteriaBuilder
                    .equal(root.get("office"),searchAdmin.getOffice());
            predicates.add(officePredicate);
        }
        if (searchAdmin.isExpired()){
            Predicate expiresPredicate;
            expiresPredicate = criteriaBuilder.lessThan(root.get("expiresAt"), Instant.now());
            Predicate memberRolePredicate = criteriaBuilder.equal(root.get("role"), Role.MEMBER);
            Predicate finalPredicate = criteriaBuilder.and(expiresPredicate, memberRolePredicate);
            predicates.add(finalPredicate);
        }
        if (searchAdmin.getRole()!= null  ){
        Predicate rolePredicate = criteriaBuilder.equal(root.get("role"), searchAdmin.getRole());
        predicates.add(rolePredicate);
        }
        criteriaQuery.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[0]))
        );
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        List<User> users = query.getResultList();
        List<UserResponse> userDto = new ArrayList<>();
        for (User user:users) {
            UserResponse member = UserResponse.makeUser(user);
            userDto.add(member);
        }
        return userDto;
    }

    @Override
    public void changePassword(PasswordChangeRequest passwordChangeRequest, Long id) {

        User user = userRepository.findById(id).orElseThrow(
                ()-> new NotFoundException(String.format("this user with id [%s] is not exist",id)));

        if (!passwordEncoder.matches(passwordChangeRequest.getOldPassword(),user.getPassword())){
            throw new ConflictException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
        userRepository.save(user);

    }
}