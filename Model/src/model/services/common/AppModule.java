package model.services.common;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue May 25 10:50:57 BDT 2021
// ---------------------------------------------------------------------
public interface AppModule extends ApplicationModule {
    void setSessionValues(String orgId, String userId, String respId,
                          String respAppl);
}
