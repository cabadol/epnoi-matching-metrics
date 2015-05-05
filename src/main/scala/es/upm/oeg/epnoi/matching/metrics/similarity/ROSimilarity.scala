package es.upm.oeg.epnoi.matching.metrics.similarity

import es.upm.oeg.epnoi.matching.metrics.domain.SemanticResource
import org.apache.spark.rdd.RDD

/**
 * Similarity metric for Research Objects
 */
object ROSimilarity {

  /**
   * Research Object Similarity
   * @param r1
   * @param r2
   * @return Double
   */
  def apply (r1: SemanticResource, r2: SemanticResource): Double={

    // First approach: Only similarity based on textual content
    TopicsSimilarity(r1,r2)
  }


  /**
   * Similarity matrix from cartesian product of semantic resources
   * @param semanticResources
   * @return RDD[(SemanticResource,Iterable[(SemanticResource,SemanticResource,Double)])]
   */
  def cross(semanticResources: RDD[SemanticResource]): RDD[(SemanticResource,Iterable[(SemanticResource,SemanticResource,Double)])]={
    semanticResources.cartesian(semanticResources).map{case(sr1,sr2)=>(sr1,sr2,apply(sr1,sr2))}.groupBy(_._1)
  }

}
