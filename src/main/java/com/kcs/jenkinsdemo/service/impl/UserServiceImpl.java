package com.kcs.jenkinsdemo.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kcs.jenkinsdemo.core.User;
import com.kcs.jenkinsdemo.dto.ResponseVO;
import com.kcs.jenkinsdemo.dto.UserVO;
import com.kcs.jenkinsdemo.enums.UserRole;
import com.kcs.jenkinsdemo.repository.UserRepository;
import com.kcs.jenkinsdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseVO<List<UserVO>> findAll() {
		List<User> users = userRepository.findAll();
		return ResponseVO.create(200, users.stream().map(obj -> UserVO.convert(obj)).collect(Collectors.toList()));
	}

	@Override
	public ResponseVO<UserVO> findById(Long id) {
		Optional<User> userOpt = userRepository.findById(id);

		if (userOpt.isPresent()) {
			return ResponseVO.create(200, UserVO.convert(userOpt.get()));
		}

		return ResponseVO.create(200, "User with provide id not exist", null);
	}

	@Override
	public ResponseVO<Long> save(UserVO vo) {
		Long persistedId = null;

		try {
			if (Objects.isNull(vo)) {
				return ResponseVO.create(400, "User can not be null", persistedId);
			}

			if (StringUtils.isBlank(vo.getName())) {
				return ResponseVO.create(400, "Name can not be null", persistedId);
			}

			UserRole userRole = null;
			if (StringUtils.isBlank(vo.getUserRole())) {
				return ResponseVO.create(400, "User role can not be null", persistedId);
			} else {
				userRole = UserRole.from(vo.getUserRole());

				if (Objects.isNull(userRole)) {
					return ResponseVO.create(400,
							"Enter valid value for user role. Possible value for user role are ADMIN, USER, GUEST ",
							persistedId);
				}
			}

			if (StringUtils.isBlank(vo.getStatus())) {
				return ResponseVO.create(400, "Status can not be null", persistedId);
			} else if (!StringUtils.equalsIgnoreCase(vo.getStatus(), "Active")
					&& !StringUtils.equalsIgnoreCase(vo.getStatus(), "Inactive")) {
				return ResponseVO.create(400, "Provide valid value for Status. Possible values are Active and Inactive",
						persistedId);
			}

			// Check that user is already exist for same name
			Optional<User> userOpt = userRepository.findOneByName(vo.getName());

			if (userOpt.isPresent()) {
				return ResponseVO.create(400, "User with provide name already exist", persistedId);
			}

			User user = new User();
			user.setName(vo.getName());
			user.setUserRole(userRole);
			user.setStatus(StringUtils.equalsIgnoreCase(vo.getStatus(), "Active") ? true : false);
			User savedUser = userRepository.save(user);

			persistedId = savedUser.getId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

			return ResponseVO.create(500, "Error in persisting User", persistedId);
		}
		return ResponseVO.create(200, "User created successfully", persistedId);
	}

	@Override
	public ResponseVO<Long> update(UserVO vo) {
		Long persistedId = null;

		try {
			User user = null;

			if (Objects.isNull(vo)) {
				return ResponseVO.create(400, "User can not be null", persistedId);
			}

			if (Objects.isNull(vo.getId())) {
				return ResponseVO.create(400, "Id can not be null", persistedId);
			} else {
				Optional<User> userOpt = userRepository.findById(vo.getId());

				if (!userOpt.isPresent()) {
					return ResponseVO.create(400, "User with provide id not found", persistedId);
				}

				user = userOpt.get();
			}

			// Check that user is already exist for same name
			if (StringUtils.isNotBlank(vo.getName())) {
				Optional<User> userOpt = userRepository.findOneByName(vo.getName());

				if (userOpt.isPresent() && !Objects.equals(user.getId(), userOpt.get().getId())) {
					return ResponseVO.create(400, "User with provide name already exist", persistedId);
				}

				user.setName(vo.getName());
			}

			UserRole userRole = null;
			if (StringUtils.isNotBlank(vo.getUserRole())) {
				userRole = UserRole.from(vo.getUserRole());

				if (Objects.isNull(userRole)) {
					return ResponseVO.create(400,
							"Enter valid value for user role. Possible value for user role are ADMIN, USER, GUEST ",
							persistedId);
				}

				user.setUserRole(userRole);
			}

			if (StringUtils.isNotBlank(vo.getStatus())) {

				if (!StringUtils.equalsIgnoreCase(vo.getStatus(), "Active")
						&& !StringUtils.equalsIgnoreCase(vo.getStatus(), "Inactive")) {
					return ResponseVO.create(400,
							"Provide valid value for Status. Possible values are Active and Inactive", persistedId);
				}

				user.setStatus(StringUtils.equalsIgnoreCase(vo.getStatus(), "Active") ? true : false);
			}

			User savedUser = userRepository.save(user);

			persistedId = savedUser.getId();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

			return ResponseVO.create(500, "Error in updating User", persistedId);
		}

		return ResponseVO.create(200, "User updated successfully", persistedId);
	}

	@Override
	public ResponseVO<Void> delete(Long id) {
		try {
			Optional<User> userOpt = userRepository.findById(id);

			if (userOpt.isPresent()) {
				userRepository.delete(userOpt.get());

				return ResponseVO.create(200, "User deleted successfully");
			}

			return ResponseVO.create(400, "User with provide id not exist");
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

			return ResponseVO.create(500, "Error in deleting User");
		}
	}
}
