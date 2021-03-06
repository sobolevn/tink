// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
////////////////////////////////////////////////////////////////////////////////

package com.google.crypto.tink.subtle;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;


/**
 * StreamSegmentDecrypter is a helper class that decrypts individual segments of a stream.
 * Instances of this interfaces are passed to ...DecryptingChannel.
 * Each instance must be initialized with the header of the ciphertext.
 */
public interface StreamSegmentDecrypter {
  void init(
      ByteBuffer header,
      byte[] aad)
      throws GeneralSecurityException;
  
  /**
   * Decrypts a ciphetext segment.
   * @param segmentNr the number of the segment
   * @param isLastSegment true if this segment is the last segment of the ciphertext stream.
   *        The last segment is encrypted (or authenticated) differently to detect truncated
   *        ciphertext.
   * @param plaintext the decrypted plaintext.
   * @throws GeneralSecurityException if ciphertext was not a valid ciphertext for the given
   *         segment.
   */
  void decryptSegment(
      ByteBuffer ciphertext,
      int segmentNr,
      boolean isLastSegment,
      ByteBuffer plaintext)
      throws GeneralSecurityException;
}

