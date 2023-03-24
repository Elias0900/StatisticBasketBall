package com.basket.statistics.Service;

import java.util.HashMap;
import java.util.Map;

import com.basket.statistics.Repo.JoueurRepo;
import com.basket.statistics.Repo.MatchRepo;
import com.basket.statistics.entities.Joueur;
import com.basket.statistics.entities.Stats;
import com.basket.statistics.entities.Total;
import com.basket.statistics.exception.TotalException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.transaction.Transactional;

import static com.basket.statistics.Tools.PdfTools.generatePdfFromHtml;

@Service
@Transactional
public class GenererPdfImpl implements GeneratePdfService {

     @Autowired
     private JoueurRepo joueurRepo;

    @Value("${app.storagefolder}")
    private String storageFolder;

    @Value(value = "${backend.url}")
    private String backEndUrl;

    @Autowired
    private Configuration freemarkerConfig;

    @Override
    public String generatePdfTotal(Long joueurId, Long matchId) throws Exception {
        Joueur joueurOptional = joueurRepo.getJoueurById(joueurId);
        if (joueurOptional != null) {
            freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates");
            Template template = freemarkerConfig.getTemplate("total.ftl");

            Map<String, Object> model = new HashMap<String, Object>();
            model.put("backEndUrl", backEndUrl);


            model.put("joueur", joueurOptional);


            Stats stat = joueurOptional.getStats().stream()
                    .filter(statist -> statist.getMatchId() == matchId)
                    .findFirst()
                    .orElse(null);

            if (stat.getTotal() != null) {
                Total total = stat.getTotal();

                model.put("total", total);
                model.put("totalPoints", total.getTotalPoints());
                model.put("totalTir2", total.getPourcentageDeuxPts());
                model.put("totalTir3", total.getPourcentageTroisPts());
                model.put("totalTirLF", total.getPourcentageLF());
                model.put("totalTir", total.getPourcentage());
                model.put("totalPasse", total.getTotalPasseD());
                model.put("totalRebond", total.getTotalRebonds());
                model.put("totalContre", total.getTotalContre());
                model.put("totalSteal", total.getTotalInterception());



                String htmlContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                String outputPdf = storageFolder + "/total-" + joueurOptional.getNom() + "_" + joueurOptional.getPrenom() + ".pdf";
                generatePdfFromHtml(outputPdf, htmlContent);

                return outputPdf;
            }else {
                throw new TotalException("Ce total n'est pas disponible");
            }

        }
        return null;
    }




}
