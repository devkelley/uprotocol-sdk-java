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

package org.eclipse.uprotocol.cloudevent.serialize;

import java.util.Base64;

/**
 * Helper for serializing Base64 protobuf data.
 */
public interface Base64ProtobufSerializer {

    /**
     * Deserialize a base64 protobuf payload into a Base64 String.
     * @param bytes byte[] data
     * @return Returns a String from the base64 protobuf payload.
     */
    static String deserialize(byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Serialize a String into Base64 format.
     * @param stringToSerialize String to serialize.
     * @return Returns the Base64 formatted String as a byte[].
     */
    static  byte[] serialize(String stringToSerialize) {
        if (stringToSerialize == null) {
            return new byte[0];
        }
        return Base64.getDecoder().decode(stringToSerialize.getBytes());
    }
}
