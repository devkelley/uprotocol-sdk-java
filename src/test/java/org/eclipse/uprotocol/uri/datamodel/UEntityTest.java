/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.eclipse.uprotocol.uri.datamodel;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UEntityTest {

    @Test
    @DisplayName("Make sure the equals and hash code works")
    public void testHashCodeEquals() {
        EqualsVerifier.forClass(UEntity.class).usingGetClass().verify();
    }

    @Test
    @DisplayName("Make sure the toString works")
    public void testToString() {
        UEntity use = new UEntity("body.access", "1");
        assertEquals("body.access", use.name());
        assertTrue(use.version().isPresent());
        assertEquals("1", use.version().get());

        String expected = "UEntity{name='body.access', version='1'}";
        assertEquals(expected, use.toString());

        UEntity use1 = UEntity.fromName("body.access");
        assertEquals("UEntity{name='body.access', version='latest'}", use1.toString());
    }

    @Test
    @DisplayName("Test creating a complete USE")
    public void test_create_use() {
        UEntity use = new UEntity("body.access", "1");
        assertEquals("body.access", use.name());
        assertTrue(use.version().isPresent());
        assertEquals("1", use.version().get());
    }

    @Test
    @DisplayName("Test creating a complete USE with a null name, expect exception")
    public void test_create_use_null_name() {
        Exception exception = assertThrows(NullPointerException.class, () -> new UEntity(null, "1"));
        assertTrue(exception.getMessage().contains(" Software Entity must have a name"));
    }

    @Test
    @DisplayName("Test creating a USE with no version")
    public void test_create_use_with_no_version() {
        UEntity use = new UEntity("body.access", " ");
        assertEquals("body.access", use.name());
        assertTrue(use.version().isEmpty());

        UEntity use2 = new UEntity("body.access", null);
        assertEquals("body.access", use2.name());
        assertTrue(use2.version().isEmpty());
    }

    @Test
    @DisplayName("Test creating a USE using the fromName static method")
    public void test_create_use_with_no_version_using_fromName() {
        UEntity use = UEntity.fromName("body.access");
        assertEquals("body.access", use.name());
        assertTrue(use.version().isEmpty());
    }

    @Test
    @DisplayName("Test creating an empty USE using the empty static method")
    public void test_create_empty_using_empty() {
        UEntity use = UEntity.empty();
        assertTrue(use.name().isEmpty());
        assertTrue(use.version().isEmpty());
    }

    @Test
    @DisplayName("Test the isEmpty static method")
    public void test_is_empty() {
        UEntity use = UEntity.empty();
        assertTrue(use.isEmpty());

        UEntity use2 = new UEntity("", null);
        assertTrue(use2.isEmpty());

        UEntity use3 = new UEntity("", "1");
        assertFalse(use3.isEmpty());

        UEntity use4 = new UEntity("petapp", null);
        assertFalse(use4.isEmpty());
    }
}