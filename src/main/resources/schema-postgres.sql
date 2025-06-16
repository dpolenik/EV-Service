
CREATE TABLE vehicle (
                         id                BIGSERIAL PRIMARY KEY,
                         MAKE              VARCHAR(64),
                         MODEL             VARCHAR(64),
                         MODEL_YEAR        VARCHAR(4),
                         EV_TYPE           VARCHAR(64),
                         CAFV_ELIGIBILITY  VARCHAR(128),
                         ELECTRIC_RANGE    SMALLINT,
                         BASE_MSRP         NUMERIC(10,2),

                         createdAt        TIMESTAMP WITH TIME ZONE DEFAULT NOW()
);

------------------------------------------------------------------------
CREATE TABLE sale (
                      id                   BIGSERIAL PRIMARY KEY,
                      vin                  VARCHAR(17)  NOT NULL UNIQUE,

                      VEHICLE_ID           BIGINT       NOT NULL,

                      county               VARCHAR(64),
                      city                 VARCHAR(64),
                      state                CHAR(2),
                      POSTAL_CODE          VARCHAR(10),
                      VEHICLE_LOCATION     VARCHAR(128),
                      LEGISLATIVE_DISTRICT VARCHAR(16),
                      DOL_VEHICLE_ID       VARCHAR(64),
                      ELECTRIC_UTILITY     VARCHAR(128),

                      CENSUS_TRACK2020    VARCHAR(20),

                      created_at           TIMESTAMP WITH TIME ZONE DEFAULT NOW(),

                      CONSTRAINT fk_sale_vehicle
                          FOREIGN KEY (VEHICLE_ID) REFERENCES vehicle(id)
                              ON DELETE RESTRICT
);

CREATE INDEX idx_sale_vin         ON sale(vin);
CREATE INDEX idx_sale_vehicle_id  ON sale(VEHICLE_ID);
