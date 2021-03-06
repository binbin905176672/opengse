// Copyright 2007 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.opengse.util;

import junit.framework.TestCase;

import org.easymock.classextension.EasyMock;

/**
 * @author Mike Jennings
 */
public class WorkerThreadTest extends TestCase {

  public void testRun() throws Exception {
    RunnableQueue q = EasyMock.createMock(RunnableQueue.class);
    Runnable task1 = EasyMock.createMock(Runnable.class);
    Runnable task2 = EasyMock.createMock(Runnable.class);
    EasyMock.expect(q.remove()).andReturn(task1);
    task1.run();
    EasyMock.expectLastCall();
    EasyMock.expect(q.remove()).andReturn(task2);
    task2.run();
    EasyMock.expectLastCall();
    EasyMock.expect(q.remove()).andThrow(new InterruptedException());
    EasyMock.replay(q, task1, task2);
    WorkerThread worker = new WorkerThread(q, "test thread");
    worker.run();
    EasyMock.verify(q, task1, task2);
  }
}
