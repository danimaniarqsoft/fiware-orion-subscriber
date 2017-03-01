/*
 *  
 * The MIT License (MIT)
 * Copyright (c) 2016 Daniel Cortes Pichardo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package mx.infotec.dads.orion.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.AuthorityUtils;

import mx.infotec.dads.orion.model.user.Address;
import mx.infotec.dads.orion.model.user.Disease;
import mx.infotec.dads.orion.model.user.HealthProfile;
import mx.infotec.dads.orion.model.user.User;
import mx.infotec.dads.orion.model.user.UserProfile;

/**
 * QueryUtil used for common Queries operations
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class QueryUtil {
    private QueryUtil() {
    }

    public static User createByExampleUser(String... roles) {
        User user = new User(null, null, true, true, true, true,
                (roles.length == 0) ? null : AuthorityUtils.createAuthorityList(roles));
        return user;
    }

    public static User createByExampleUser() {
        return createByExampleUser(new String[] {});
    }

    public static User createUserTemplate(){
        User user = createByExampleUser();
        user.setUsername("");
        user.setPassword("");
        UserProfile up = new UserProfile();
        up.setBirthdate("");
        up.setFamilyName("");
        up.setName("");
        up.setEmail("");
        up.setGender("");
        Address address = new Address();
        address.setAddresCountry("");
        address.setAddressLocality("");
        address.setAddressRegion("");
        address.setPostalCode("");
        address.setStreet("");
        up.setAddress(address);
        HealthProfile hp = new HealthProfile();
        hp.setHealthstate("");
        hp.setDiseases(new ArrayList<>());
        up.setHealthProfile(hp);
        user.setUserProfile(up);
        return user;
    }
    public static User createDefaulUserOne() {
        User user = new User(null, null, true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
        user.setUsername("blanca.vazquez");
        user.setPassword("blanca.vazquez");
        UserProfile up = new UserProfile();
        up.setBirthdate("10/12/1998");
        up.setFamilyName("Vazquez Gomez");
        up.setName("Blanca Hilda");
        up.setEmail("blanca.vazquez@infotec.mx");
        up.setGender("Female");
        Address address = new Address();
        address.setAddresCountry("México");
        address.setAddressLocality("Ciudad de México");
        address.setAddressRegion("Tlalpan");
        address.setPostalCode("03996");
        address.setStreet("Av. Tlalpan");
        up.setAddress(address);
        HealthProfile hp = new HealthProfile();
        hp.setHealthstate("Good");
        List<Disease> diseases = new ArrayList<>();
        Disease disease = new Disease();
        disease.setId("HD");
        disease.setName("Heart Disease");
        diseases.add(disease);
        hp.setDiseases(diseases);
        up.setHealthProfile(hp);
        user.setUserProfile(up);
        return user;
    }

    public static User createDefaulUserTwo() {
        User user = new User(null, null, true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
        user.setUsername("karen.najera");
        user.setPassword("karen.najera");
        UserProfile up = new UserProfile();
        up.setBirthdate("12/07/1996");
        up.setFamilyName("Najera Hernandez");
        up.setName("Karen Mariel");
        up.setEmail("karen.najera@infotec.mx");
        up.setGender("Female");
        Address address = new Address();
        address.setAddresCountry("México");
        address.setAddressLocality("Ciudad de México");
        address.setAddressRegion("Tlalpan");
        address.setPostalCode("02346");
        address.setStreet("Av. Tlalpan");
        up.setAddress(address);
        HealthProfile hp = new HealthProfile();
        hp.setHealthstate("Good");
        List<Disease> diseases = new ArrayList<>();
        Disease disease = new Disease();
        disease.setId("HD");
        disease.setName("Heart Disease");
        diseases.add(disease);
        hp.setDiseases(diseases);
        up.setHealthProfile(hp);
        user.setUserProfile(up);
        return user;
    }
}
