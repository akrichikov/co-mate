package org.archguard.meta

enum class DynamicPromptContext(val value: String) {
    API_SPECIFICATION("ApiSpecification") {
        override fun explain(): String {
            return """ApiSpecification is a specification of a REST API.""".trimIndent()
        }

    },
    FOUNDATION_SPECIFICATION("FoundationSpecification") {
        override fun explain(): String {
            return """FoundationSpecification is a specification of a naming style, package naming, class naming..""".trimIndent()
        }
    },
    LAYERED("Layered") {
        override fun explain(): String {
            return """Layered is a define for layered architecture.""".trimIndent()
        }
    },
    SERVICE_MAP("ServiceMap") {
        override fun explain(): String {
            return """ServiceMap is a define for service map.""".trimIndent()
        }
    },
    DOMAIN_MODEL("DomainModel") {
        override fun explain(): String {
            return """DomainModel is a define for domain model.""".trimIndent()
        }
    }
    ;

    abstract fun explain(): String;

    companion object {
        fun from(value: String): DynamicPromptContext? {
            return values().find { it.value == value }
        }

        fun build(values: List<String>): List<String> = values.mapNotNull(::from).map {
            when (it) {
                API_SPECIFICATION -> {
                    "ApiSpecification"
                }

                FOUNDATION_SPECIFICATION -> {
                    "FoundationSpecification"
                }

                LAYERED -> "Layered"
                SERVICE_MAP -> "ServiceMap"
                DOMAIN_MODEL -> "DomainModel"
            }
        }

        /**
         * With this method, you can get all the explanations of the context.
         */
        fun explains(): HashMap<String, String> {
            val map = HashMap<String, String>()
            values().forEach {
                map[it.value] = it.explain()
            }

            return map
        }
    }
}