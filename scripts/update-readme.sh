#!/bin/bash
set -e

# Generate Swagger markdown (API_DOCS.md)
npx swagger-markdown -i http://localhost:8081/v3/api-docs -o API_DOCS.md

# Replace the section inside README.md markers
awk '
  /<!-- AUTO-GENERATED-API-DOCS -->/ {print; system("cat API_DOCS.md"); found=1; next}
  /<!-- END-AUTO-GENERATED-API-DOCS -->/ {found=0}
  !found
' README.md > README.tmp && mv README.tmp README.md

# Cleanup
rm -f API_DOCS.md