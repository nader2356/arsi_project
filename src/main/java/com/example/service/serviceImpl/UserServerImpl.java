package com.example.service.serviceImpl;



import com.example.dto.requestDto.PasswordChangeRequest;
import com.example.dto.requestDto.UpdateMemberRequest;
import com.example.dto.requestDto.UpdateUserRequest;
import com.example.dto.responseDto.UserResponse;
import com.example.dto.searchRequest.SearchAdmin;
import com.example.entity.User;
import com.example.exception.ConflictException;
import com.example.exception.NotFoundException;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.util.EmailUtil;
import com.example.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServerImpl implements UserService {
    private final UserRepository userRepository;
    private final EntityManager em;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private OtpUtil otpUtil;
    @Autowired
    private EmailUtil emailUtil;



    @Override
    public List<UserResponse> getAllMember() {

        List<User> users = userRepository.findAllMember();
        List<UserResponse> members = new ArrayList<>();
        for (User user : users) {
            UserResponse member = UserResponse.makeUser(user);
            members.add(member);
        }
        return members;
    }
    @Override
    public UserResponse getMemberById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("this user with id [%s] not exist", id)));
        return UserResponse.makeUser(user);
    }
    @Override
    public void updateMember(Long id, UpdateMemberRequest request) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("this user with id [%s] not exist", id)));
        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException(String.format("this email is already exist ( [%s] ) ", request.getEmail()));
        }
        if (!user.getUsername().equals(request.getUserName()) && userRepository.existsByUserName(request.getUserName())) {
            throw new ConflictException(String.format("this userName is already exist ( [%s] ) ", request.getUserName()));
        }

   

        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getUserName() != null) {
            user.setUserName(request.getUserName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getGender() != null) {
            user.setGender(request.getGender());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getRegion() != null) {
            user.setRegion(request.getRegion());
        }
        if (request.getImage() != null) {
            user.setImage(request.getImage());
        }
        if (request.getCv() != null) {
            user.setCv(request.getCv());
        }
        if (request.getDateOfBirth() != null) {
            user.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getJob() != null) {
            user.setJob(request.getJob());
        }
        if (request.getUniversityOrCompany() != null) {
            user.setUniversityOrCompany(request.getUniversityOrCompany());
        }
        if (request.getOffice() != null) {
            user.setOffice(request.getOffice());
        }

        userRepository.save(user);

    }
    @Override
    public void updateUser(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("this user with id [%s] not exist", id)));
        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException(String.format("this email is already exist ( [%s] ) ", request.getEmail()));
        }
        if (!user.getUsername().equals(request.getUserName()) && userRepository.existsByUserName(request.getUserName())) {
            throw new ConflictException(String.format("this userName is already exist ( [%s] ) ", request.getUserName()));
        }

     
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getUserName() != null) {
            user.setUserName(request.getUserName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getGender() != null) {
            user.setGender(request.getGender());
        }
        if (request.getPhoneNumber() != null) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getRegion() != null) {
            user.setRegion(request.getRegion());
        }
        if (request.getDateOfBirth() != null) {
            user.setDateOfBirth(request.getDateOfBirth());
        }
        if (request.getJob() != null) {
            user.setJob(request.getJob());
        }
        if (request.getUniversityOrCompany() != null) {
            user.setUniversityOrCompany(request.getUniversityOrCompany());
        }
        if (request.getOffice() != null) {
            user.setOffice(request.getOffice());
        }
        if (request.getPost() != null) {
            user.setPost(request.getPost());
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        if (request.getImage() != null) {
            user.setImage(request.getImage());
        }
        if (request.getCv() != null) {
            user.setCv(request.getCv());
        }

        userRepository.save(user);

    }
    @Override
    public UserResponse getConnectedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Optional<User> user = userRepository.findByUserName(currentUserName);
        if (user.isPresent()) {
            return UserResponse.makeUser(user.get());
        } else throw new RuntimeException("mafamech User *************");
    }
    @Override
    public void deleteMember(Long id) {
        userRepository.deleteById(id);
    }
    @Override
    public void enableMember(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        user.setPaid(true);
        user.setStatus(!user.isStatus());
        userRepository.save(user);
    }





    @Override
    public Page<UserResponse> getAllUserByFilter(SearchAdmin searchAdmin, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> root = criteriaQuery.from(User.class);
        if (searchAdmin.getId() != null) {
            Predicate idPredicate = criteriaBuilder
                    .equal(root.get("id"), searchAdmin.getId());
            predicates.add(idPredicate);
        }
        if (searchAdmin.getFirstName() != null) {
            Predicate firstNamePredicate = criteriaBuilder
                    .like(root.get("firstName"), "%" + searchAdmin.getFirstName() + "%");
            predicates.add(firstNamePredicate);
        }
        if (searchAdmin.getLastName() != null) {
            Predicate lastNamePredicate = criteriaBuilder
                    .like(root.get("lastName"), "%" + searchAdmin.getLastName() + "%");
            predicates.add(lastNamePredicate);
        }
        if (searchAdmin.getGender() != null) {
            Predicate genderPredicate = criteriaBuilder
                    .equal(root.get("gender"), searchAdmin.getGender());
            predicates.add(genderPredicate);
        }
        if (searchAdmin.getRegion() != null) {
            Predicate regionPredicate = criteriaBuilder
                    .like(root.get("region"), "%" + searchAdmin.getRegion() + "%");
            predicates.add(regionPredicate);
        }
        if (searchAdmin.getJob() != null) {
            Predicate jobPredicate = criteriaBuilder
                    .like(root.get("job"), "%" + searchAdmin.getJob() + "%");
            predicates.add(jobPredicate);
        }
        if (searchAdmin.getUniversityOrCompany() != null) {
            Predicate universityOrCompanyPredicate = criteriaBuilder
                    .like(root.get("universityOrCompany"), "%" + searchAdmin.getUniversityOrCompany() + "%");
            predicates.add(universityOrCompanyPredicate);
        }
        if (searchAdmin.getPost() != null) {
            Predicate postPredicate = criteriaBuilder
                    .equal(root.get("post"), searchAdmin.getPost());
            predicates.add(postPredicate);
        }
        if (searchAdmin.getOffice() != null) {
            Predicate officePredicate = criteriaBuilder
                    .equal(root.get("office"), searchAdmin.getOffice());
            predicates.add(officePredicate);
        }
        if (searchAdmin.getRole() != null) {
            Predicate rolePredicate = criteriaBuilder.equal(root.get("role"), searchAdmin.getRole());
            predicates.add(rolePredicate);
        }
//      if (!searchAdmin.isStatus()) {
//      Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), false);
//      predicates.add(statusPredicate);
//  }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        // Apply pagination
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        List<User> users = query.getResultList();
        // Convert User entities to UserResponse DTOs
        List<UserResponse> userDto = new ArrayList<>();
        for (User user : users) {
            UserResponse member = UserResponse.makeUser(user);
            userDto.add(member);
        }
        // Create a Page<UserResponse> using the results and pageable
        long totalCount = countUsersByFilter(searchAdmin); // You'll need to implement this method to count total records.
        return new PageImpl<>(userDto, pageable, totalCount);

    }

    public long countUsersByFilter(SearchAdmin searchAdmin) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<User> root = criteriaQuery.from(User.class);
        if (searchAdmin.getId() != null) {
            Predicate idPredicate = criteriaBuilder
                    .equal(root.get("id"), searchAdmin.getId());
            predicates.add(idPredicate);
        }
        if (searchAdmin.getFirstName() != null) {
            Predicate firstNamePredicate = criteriaBuilder
                    .like(root.get("firstName"), "%" + searchAdmin.getFirstName() + "%");
            predicates.add(firstNamePredicate);
        }
        if (searchAdmin.getLastName() != null) {
            Predicate lastNamePredicate = criteriaBuilder
                    .like(root.get("lastName"), "%" + searchAdmin.getLastName() + "%");
            predicates.add(lastNamePredicate);
        }
        if (searchAdmin.getGender() != null) {
            Predicate genderPredicate = criteriaBuilder
                    .equal(root.get("gender"), searchAdmin.getGender());
            predicates.add(genderPredicate);
        }
        if (searchAdmin.getRegion() != null) {
            Predicate regionPredicate = criteriaBuilder
                    .like(root.get("region"), "%" + searchAdmin.getRegion() + "%");
            predicates.add(regionPredicate);
        }
        if (searchAdmin.getJob() != null) {
            Predicate jobPredicate = criteriaBuilder
                    .like(root.get("job"), "%" + searchAdmin.getJob() + "%");
            predicates.add(jobPredicate);
        }
        if (searchAdmin.getUniversityOrCompany() != null) {
            Predicate universityOrCompanyPredicate = criteriaBuilder
                    .like(root.get("universityOrCompany"), "%" + searchAdmin.getUniversityOrCompany() + "%");
            predicates.add(universityOrCompanyPredicate);
        }
        if (searchAdmin.getPost() != null) {
            Predicate postPredicate = criteriaBuilder
                    .equal(root.get("post"), searchAdmin.getPost());
            predicates.add(postPredicate);
        }
        if (searchAdmin.getOffice() != null) {
            Predicate officePredicate = criteriaBuilder
                    .equal(root.get("office"), searchAdmin.getOffice());
            predicates.add(officePredicate);
        }
        if (searchAdmin.getRole() != null) {
            Predicate rolePredicate = criteriaBuilder.equal(root.get("role"), searchAdmin.getRole());
            predicates.add(rolePredicate);
        }
        // Your existing code to build predicates here...
        criteriaQuery.select(criteriaBuilder.count(root));
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<Long> query = em.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public void changePassword(PasswordChangeRequest passwordChangeRequest, Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("this user with id [%s] is not exist", id)));
        if (!passwordEncoder.matches(passwordChangeRequest.getOldPassword(), user.getPassword())) {
            throw new ConflictException("Old password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));
        userRepository.save(user);
    }
    @Transactional
    public void forgotPassword(String username) {
        User user = userRepository.findByUserName(username).orElseThrow();
        String otp = otpUtil.generateOTP();
        user.setOtp(otp);
        userRepository.save(user);
        emailUtil.sendOTPEmail(user.getEmail(), otp);
    }
    @Transactional
    public void resetPasswordWithOTP(String username, String otp, String newPassword) {
        User user = userRepository.findByUserName(username).orElseThrow();
        if (otp.equals(user.getOtp())) {
            String hashedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(hashedPassword);
            user.setOtp(null);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Invalid OTP or user not found.");
        }
    }




}