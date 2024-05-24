package adrianobruzzese.be-m5-w2-d5.repositories;

import adrianobruzzese.be-m5-w2-d5.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

}
