(ns biodata-validator.specs
  (:require [clojure.spec.alpha :as s]
            [clojure.java.io :as io]
            [clojure.data.json :as json]
            [clojure.string :as clj-str])
  (:import [org.apache.commons.validator.routines UrlValidator]))

(set! *warn-on-reflection* true)

;; ------------------------------- Init -------------------------------
(defonce data
  (atom {:genes nil}))

(defn load-genes
  []
  (-> "data/genes.json"
      io/resource
      slurp
      (json/read-str :key-fn keyword)))

(defn load-data
  []
  (let [genes (load-genes)]
    (reset! data (merge @data {:genes genes}))))

;; Initialize the internal data
(load-data)

;; ------------------------------- Specs -------------------------------
(defn valid-url?
  [^String url-str]
  (let [validator (UrlValidator. UrlValidator/ALLOW_ALL_SCHEMES)]
    (.isValid validator url-str)))

(s/def :biodata-validator/url (s/and string? valid-url?))

(defn valid-age?
  [^Number age]
  (and (> age 0) (<= age 120)))

(s/def :biodata-validator/age (s/and (s/or :float float? :integer integer?) valid-age?))

(defn valid-gender?
  [^String gender]
  (some? ((keyword gender) #{:Female :Male :female :male :FEMALE :MALE :F :M :f :m})))

(s/def :biodata-validator/gender (s/and string? valid-gender?))

(defn valid-gender-number?
  [^Number gender-number]
  (or (= gender-number 0) (= gender-number 1)))

(s/def :biodata-validator/gender-number (s/and integer? valid-gender-number?))

(defn- valid-id?
  "TODO: Better? Several ids in one record?"
  [^clojure.lang.Keyword symbol ^String id]
  (assert (symbol #{:hgnc_id :approved_symbol :chromosome :entrez_id
                    :omim_id :refseq_id :uniprot_id :ensembl_gene_id
                    :ucsc_id :rat_genome_db_id :lncipedia_id :gtrnadb_id})
          (format "%s is an invalid ID" symbol))
  (let [genes (:genes @data)
        matched (filter true? (pmap (fn [gene] (= (symbol gene) id)) genes))]
    (> (count matched) 0)))

(def valid-hgnc-id? (partial valid-id? :hgnc_id))

(def valid-gene-symbol? (partial valid-id? :approved_symbol))

(def valid-entrez-id? (partial valid-id? :entrez_id))

(def valid-omim-id? (partial valid-id? :omim_id))

(def valid-refseq-id? (partial valid-id? :refseq_id))

(def valid-uniprot-id? (partial valid-id? :uniprot_id))

(def valid-ensembl-gene-id? (partial valid-id? :ensembl_gene_id))

(def valid-ucsc-id? (partial valid-id? :ucsc_id))

(def valid-rat-genome-db-id? (partial valid-id? :rat_genome_db_id))

(def valid-lncipedia-id? (partial valid-id? :lncipedia_id))

(def valid-gtrnadb-id? (partial valid-id? :gtrnadb_id))
