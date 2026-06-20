package pl.pjatk.jaz_s32945_nbp.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.jaz_s32945_nbp.Models.CurrencyLog;

public interface CurrencyRepo extends JpaRepository<CurrencyLog, Long> {
}
