#!/bin/bash
set -e

# Generate Swagger markdown (API_DOCS.md)
npx swagger-markdown -i http://localhost:8080/v3/api-docs -o API_DOCS.md

# Replace API docs section
awk '
  /<!-- AUTO-GENERATED-API-DOCS -->/ {print; system("cat API_DOCS.md"); found=1; next}
  /<!-- END-AUTO-GENERATED-API-DOCS -->/ {found=0}
  !found
' README.md > README.tmp && mv README.tmp README.md

# Handle versioning
VERSION=$(cat .readme-version)
NEXT_VERSION=$((VERSION + 1))
echo $NEXT_VERSION > .readme-version

# Replace README version section
awk -v ver=$NEXT_VERSION '
  /<!-- README-VERSION -->/ {print; print "Current version: v"ver; found=1; next}
  /<!-- END-README-VERSION -->/ {found=0}
  !found
' README.md > README.tmp && mv README.tmp README.md

rm -f API_DOCS.md
