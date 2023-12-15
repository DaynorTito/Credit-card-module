package org.company.com.domain.DAO;

import java.io.Serializable;

public interface EntityDAO<ID> extends Serializable {
    ID getId();
}
