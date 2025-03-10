package com.MyStudentApp.model.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SHA256WithSaltVerification {

    /**
     * Generates a random salt.
     * Using SecureRandom ensures that the salt is unpredictable.
     * A 16-byte salt is used here, which is generally a good length.
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];  // 16 bytes = 128 bits of salt
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }



    /**
     * Hashes the input string with the provided salt using SHA-256.
     *
     * @param input the plaintext to hash
     * @param HashedSalt the salt to mix into the hash
     * @return a Base64 encoded string of the hash
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    public static String hashWithSalt(String input, String HashedSalt) throws NoSuchAlgorithmException {
        // Obtain a MessageDigest instance for SHA-256

        byte[] salt = Base64.getDecoder().decode(HashedSalt);
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Update the digest with the salt bytes.
        // This mixes the salt into the hash computation.
        md.update(salt);

        // Compute the hash of the input text.
        // Converting the input to bytes (using the default charset)
        byte[] hashBytes = md.digest(input.getBytes());

        // Encode the resulting hash to Base64 so it can be stored or displayed as a string.
        return Base64.getEncoder().encodeToString(hashBytes);
    }

    /**
     * Verifies a password by hashing it with the provided salt and comparing it to the stored hash.
     *
     * @param password the password to verify
     * @param storedHash the previously computed hash (in Base64)
     * @param HashedSalt the salt used to generate the stored hash
     * @return true if the password is correct, false otherwise
     * @throws NoSuchAlgorithmException if SHA-256 is not available
     */
    public static boolean verifyPassword(String password, String storedHash, String HashedSalt) throws NoSuchAlgorithmException {
        // Hash the input password using the same salt.

        String newHash = hashWithSalt(password, HashedSalt);

        // Compare the new hash with the stored hash.
        return newHash.equals(storedHash);
    }

    /*public static void main(String[] args) {
        try {
            // The original password that we want to hash and later verify.
            String originalPassword = "mypassword";

            // Generate a random salt.
            byte[] salt = generateSalt();

            // Hash the original password with the generated salt.
            String hashedPassword = hashWithSalt(originalPassword, salt);

            // For demonstration: simulate a user input for verification.
            String userInput = "mypassword";  // Try changing this value to test verification

            // Verify the user input against the stored hash.
            boolean isVerified = verifyPassword(userInput, hashedPassword, salt);

            // Convert the salt to Base64 for display/storage purposes.
            String saltBase64 = Base64.getEncoder().encodeToString(salt);

            // Display the details
            System.out.println("Original Password: " + originalPassword);
            System.out.println("Salt (Base64): " + saltBase64);
            System.out.println("Hashed Password (Base64): " + hashedPassword);
            System.out.println("Password Verification Result: " + (isVerified ? "Match" : "No match"));

        } catch (NoSuchAlgorithmException e) {
            // If SHA-256 algorithm is not available, print the error.
            e.printStackTrace();
        }
    }*/
}
