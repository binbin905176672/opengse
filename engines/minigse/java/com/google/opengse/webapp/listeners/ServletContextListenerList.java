// Copyright 2008 Google Inc. All Rights Reserved.
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

package com.google.opengse.webapp.listeners;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * @author jennings
 *         Date: Jun 1, 2008
 */
public class ServletContextListenerList implements ServletContextListener {
  private final List<ServletContextListener> listeners;

  public ServletContextListenerList() {
    listeners = new ArrayList<ServletContextListener>();
  }

  public void add(ServletContextListener listener) {
    listeners.add(listener);
  }

  public void contextInitialized(ServletContextEvent sce) {
    for (ServletContextListener listener : listeners) {
      listener.contextInitialized(sce);
    }
  }

  public void contextDestroyed(ServletContextEvent sce) {
    for (ServletContextListener listener : listeners) {
      listener.contextDestroyed(sce);
    }
  }
}
