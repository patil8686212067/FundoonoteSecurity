package com.fundoonotes.userservice;

import java.io.File;
import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fundoonotes.exception.UnAuthorizedAccessUser;
import com.fundoonotes.utility.CustomResponse;
import com.fundoonotes.utility.RegisterErrors;
import com.fundoonotes.utility.TokenUtils;
import com.fundoonotes.utility.UserValidator;

@RestController
public class UserController {

	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserValidator userValidator;

	@Value("${frontendUrl}")
	private String frontendUrl;

	@Value("${frontEndHost}")
	private String frontEndHost;

	/**
	 * <p>
	 * This API is used register the new User in DataBase
	 * </p>
	 * 
	 * @param userDto
	 * @param bindingResult
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@Validated @RequestBody UserDto userDto, BindingResult bindingResult,
			HttpServletRequest request) throws Exception {

		userValidator.validate(userDto, bindingResult);
		List<FieldError> errors = bindingResult.getFieldErrors();

		RegisterErrors response = new RegisterErrors();
		CustomResponse customRes = new CustomResponse();
		if (bindingResult.hasErrors()) {
			logger.info("This is an info log entry");
			response.setMsg("registrtion fail");
			response.setStatus(-200);

			return new ResponseEntity<RegisterErrors>(response, HttpStatus.CONFLICT);
		}

		String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));
		userService.register(userDto, url);

		response.setMsg("user register successfully");
		response.setStatus(200);

		logger.info("This is info message");

		return new ResponseEntity<RegisterErrors>(response, HttpStatus.CREATED);

	}

	/**
	 * <p>
	 * This API is used to login purpose and for successful login JWT token is
	 * return to user
	 * 
	 * @param userDto
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletResponse response) {

		CustomResponse customRes = new CustomResponse();
		String token = userService.login(userDto);
		System.out.println("this is your tooekn:" + token);
		if (token != null) {
			response.setHeader("Authorization", token);
			customRes.setMessage("user login successfully");
			customRes.setStatusCode(100);

			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.OK);
		} else {

			throw new UnAuthorizedAccessUser();
		}
	}

	/**
	 * <p>
	 * This is isActivUser API to Activate the user when user is register
	 * successfully in FundooNote application
	 * </p>
	 * 
	 * @param token
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/activateaccount/{token}", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> isActiveUser(@PathVariable("token") String token,
			HttpServletResponse response) throws IOException {

		CustomResponse customRes = new CustomResponse();

		if (userService.userActivation(token) == 1) {
			response.sendRedirect("frontendUrl");

			customRes.setMessage("user activation done successfully");
			customRes.setStatusCode(200);
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.CREATED);
		} else {

			customRes.setMessage("activation fail");
			customRes.setStatusCode(409);
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.CONFLICT);
		}

	}

	/**
	 * <p>
	 * This is forgot passeword API redirect to the user to reset him new password
	 * </p>
	 * 
	 * @param userDto
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> forgotPassword(@RequestBody UserDto userDto, HttpServletRequest request) {
		CustomResponse customRes = new CustomResponse();
		try {
			System.out.println(userDto.getEmail());
			String url = request.getRequestURL().toString().substring(0, request.getRequestURL().lastIndexOf("/"));
			if (userService.forgetPassword(userDto.getEmail(), url))

			{
				customRes.setMessage("forgot password");
				customRes.setStatusCode(100);
				return new ResponseEntity<CustomResponse>(customRes, HttpStatus.OK);
			} else {
				return new ResponseEntity<CustomResponse>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<CustomResponse>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * <p>
	 * This is resetPassword API is used to reset password
	 * </p>
	 * 
	 * @param userDto
	 * @param jwtToken
	 * @return
	 */
	@RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
	public ResponseEntity<CustomResponse> resetPassword(@RequestBody UserDto userDto,
			@RequestParam("jwtToken") String jwtToken) {

		CustomResponse customRes = new CustomResponse();
		int id = TokenUtils.verifyToken(jwtToken);
		User user = userService.getUserById(id);
		userDto.setEmail(user.getEmail());

		if (userService.resetPassword(userDto) == 1) {
			customRes.setMessage("Reset Password Sucessfully........");
			customRes.setStatusCode(100);
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.OK);

		} else {
			customRes.setMessage("Password Not Updated.......");
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * <p>
	 * This is resetPasswordLink API is used redirect the request to front
	 * resetPassword view component
	 * </p>
	 * 
	 * @param jwtToken
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/resetpasswordlink/{jwtToken:.+}", method = RequestMethod.GET)
	public void resetPasswordLink(@PathVariable("jwtToken") String jwtToken, HttpServletResponse response,
			HttpServletRequest request) throws IOException {

		logger.info("In side reset password link");
		System.out.print("url for front end-->" + request.getHeader("origin"));
		System.out.print("your fronENd url " + frontEndHost);
		response.sendRedirect(frontEndHost + "/resetpassword?jwtToken=" + jwtToken);

	}

	/**
	 * <p>
	 * This is loggedUser API used to get the currently logged user
	 * </p>
	 * 
	 * @param reuqest
	 * @return
	 */
	@RequestMapping(value = "/loggeduser", method = RequestMethod.GET)
	public ResponseEntity<?> getLoggeddUser(HttpServletRequest reuqest) {
		int userId = TokenUtils.verifyToken(reuqest.getHeader("Authorization"));
		CustomResponse customRes = new CustomResponse();
		User user = userService.getUserById(userId);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		customRes.setMessage("no logged user");
		customRes.setStatusCode(409);
		return new ResponseEntity<CustomResponse>(customRes, HttpStatus.CONFLICT);
	}

	@RequestMapping(value = "/getuser", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(HttpServletRequest request) {
		int userId = TokenUtils.verifyToken(request.getHeader("Authorization"));
		System.out.println(userId);
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);

	}

	/**
	 * <p>
	 * This API IS Used to upload Profile pic of user
	 * </p>
	 * 
	 * @param uploadProfileImage
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws SerialException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/uploadprofilepic", method = RequestMethod.PUT)
	public ResponseEntity<CustomResponse> uploadProfileImage(@RequestParam("file") MultipartFile uploadProfileImage,
			HttpServletRequest request) throws IOException, SerialException, SQLException {
		
		/*String rootApppath = request.getServletContext().getRealPath("");
		String imageFolder = rootApppath+"/images";
		File file = new File(imageFolder);
		if ( !file.exists() )
		{
			file.mkdir();
		}
		String userFolder = rootApppath+"/images/45237457";
		File file2 = new File(userFolder);
		if ( !file2.exists() )
		{
			file2.mkdir();
		}
		
		String filePath = rootApppath+"/images/45237457/imageName.png";*/
		
		CustomResponse customRes = new CustomResponse();
		int userId = TokenUtils.verifyToken(request.getHeader("Authorization"));
		System.out.println("Check image->>" + uploadProfileImage.getOriginalFilename());

		if (userId == 0) 
		{

			customRes.setMessage("Error while uploading your image");
			customRes.setStatusCode(-1);
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.CONFLICT);

		} else {
			userService.uploadImage(uploadProfileImage, userId);
			
			customRes.setMessage("your profile pic uploaded successfully");
			customRes.setStatusCode(1);
			return new ResponseEntity<CustomResponse>(customRes, HttpStatus.ACCEPTED);
		}

	}
}
