package eu.chargetime.ocpp.feature.profile;
/*
 * ChargeTime.eu - Java-OCA-OCPP
 *
 * MIT License
 *
 * Copyright (C) 2017 Emil Christopher Solli Melar <emil@iconsultable.no>
 * Copyright (C) 2019 Kevin Raddatz <kevin.raddatz@valtech-mobility.com>
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

import eu.chargetime.ocpp.feature.*;
import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.smartcharging.ClearChargingProfileRequest;
import eu.chargetime.ocpp.model.smartcharging.GetCompositeScheduleRequest;
import eu.chargetime.ocpp.model.smartcharging.SetChargingProfileRequest;

import java.util.ArrayList;
import java.util.UUID;

/** Callback handler for client events of the Smart Charging feature profile. */
public class ClientSmartChargingProfile implements Profile {
  private final ClientSmartChargingEventHandler eventHandler;
  private final ArrayList<Feature> features;

  public ClientSmartChargingProfile(ClientSmartChargingEventHandler handler) {
    features = new ArrayList<>();
    eventHandler = handler;

    features.add(new ClearChargingProfileFeature(this));
    features.add(new GetCompositeScheduleFeature(this));
    features.add(new SetChargingProfileFeature(this));
  }

  @Override
  public ProfileFeature[] getFeatureList() {
    return features.toArray(new ProfileFeature[0]);
  }

  @Override
  public Confirmation handleRequest(UUID sessionIndex, Request request) {
    Confirmation result = null;

    if (request instanceof ClearChargingProfileRequest) {
      result =
          eventHandler.handleClearChargingProfileRequest((ClearChargingProfileRequest) request);
    } else if (request instanceof GetCompositeScheduleRequest) {
      result =
          eventHandler.handleGetCompositeScheduleRequest((GetCompositeScheduleRequest) request);
    } else if (request instanceof SetChargingProfileRequest) {
      result = eventHandler.handleSetChargingProfileRequest((SetChargingProfileRequest) request);
    }

    return result;
  }
}
