(ns biodata-validator.specs
  (:require [clojure.spec.alpha :as s]
            [biodata-validator.validator :as v]))

(set! *warn-on-reflection* true)

(s/def :biodata-validator/url (s/and string? v/valid-url?))

(s/def :biodata-validator/age (s/and (s/or :float float? :integer integer?) v/valid-age?))

(s/def :biodata-validator/gender (s/and string? v/valid-gender?))

(s/def :biodata-validator/gender-number (s/and integer? v/valid-gender-number?))
